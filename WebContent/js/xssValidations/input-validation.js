(function () {

    // --------------------------------------------------------
    // CONFIGURATION
    // --------------------------------------------------------

    var TEXT_BLOCKED_CHARS = /[&#<>"'~`^{}|\\]/g;
    var NUMBER_ALLOWED_PATTERN = /[^0-9.,\-]/g;

    // Characters that will be REPLACED (shown in Old→New modal)
    // Add or remove rules as needed
   /* var REPLACE_RULES = [
        { pattern: /&/g,   replace: 'and'          },
        { pattern: /</g,   replace: 'Less Than'     },
        { pattern: />/g,   replace: 'Greater Than'  },
        { pattern: /#/g,   replace: 'hash'          },
        { pattern: /\^/g,  replace: ''              },
        { pattern: /`/g,   replace: ''              },
        { pattern: /;/g,   replace: ''              },
        { pattern: /\(/g,  replace: ''              },
        { pattern: /\)/g,  replace: ''              },
        { pattern: /\{/g,  replace: ''              },
        { pattern: /\}/g,  replace: ''              },
        { pattern: /\|/g,  replace: ''              },
        { pattern: /\\/g,  replace: ''              }
    ];*/

    var SKIP_SELECTOR = [
        'input[type="password"]',
        'input[type="hidden"]',
        'input[type="file"]',
        'input[type="checkbox"]',
        'input[type="radio"]',
        'input[type="submit"]',
        'input[type="button"]',
        'input[type="reset"]',
        'input[type="date"]',
        'input[type="time"]',
        '[data-xss-skip]'
    ].join(',');

    // --------------------------------------------------------
    // UTILITY FUNCTIONS
    // --------------------------------------------------------

    function resetRules() {
        REPLACE_RULES.forEach(function (r) { r.pattern.lastIndex = 0; });
        TEXT_BLOCKED_CHARS.lastIndex = 0;
    }

    function hasSpecialChars(value) {
        if (!value) return false;
        resetRules();
        return TEXT_BLOCKED_CHARS.test(value);
    }

    // Full replacement logic used on submit (produces new clean value)
    function applySanitize(value) {
        if (!value) return value;
        var result = value;
        REPLACE_RULES.forEach(function (r) {
            r.pattern.lastIndex = 0;
            result = result.replace(r.pattern, r.replace);
        });
        // Collapse multiple spaces left over from replacements
        return result.replace(/\s{2,}/g, ' ').trim();
    }

    // Real-time sanitize: silently remove blocked chars + show toast (no modal)
    function sanitizeRealTime(value) {
        if (!value) return value;
        resetRules();
        if (TEXT_BLOCKED_CHARS.test(value)) {
            TEXT_BLOCKED_CHARS.lastIndex = 0;
            showToast('Special characters are not allowed.');
            return value.replace(TEXT_BLOCKED_CHARS, '');
        }
        return value;
    }

    function sanitizeNumber(value) {
        if (!value) return value;
        return value.replace(NUMBER_ALLOWED_PATTERN, '');
    }

    function shouldSkip(el) {
        return el.matches && el.matches(SKIP_SELECTOR);
    }

    function isTextField(el) {
        return (
            el.tagName === 'TEXTAREA' ||
            el.type === 'text'   ||
            el.type === 'search' ||
            el.type === 'email'  ||
            el.type === 'tel'
        );
    }

    function isNumberField(el) {
        return el.type === 'number';
    }

    function escapeHtml(str) {
        return String(str)
            .replace(/&/g,  '&amp;')
            .replace(/</g,  '&lt;')
            .replace(/>/g,  '&gt;');
    }

    // --------------------------------------------------------
    // TOAST NOTIFICATION (real-time / paste errors)
    // --------------------------------------------------------

    function showToast(message) {
        var existing = document.getElementById('xss-toast');
        if (existing) existing.remove();

        var toast = document.createElement('div');
        toast.id = 'xss-toast';
        toast.innerText = message;
        toast.style.cssText =
            'position:fixed;top:40px;left:50%;transform:translateX(-50%);' +
            'background:#d32f2f;color:#fff;padding:12px 20px;border-radius:6px;' +
            'font-size:14px;z-index:999999;box-shadow:0 4px 12px rgba(0,0,0,0.2);' +
            'font-family:Arial,sans-serif;white-space:nowrap;';

        document.body.appendChild(toast);
        setTimeout(function () { if (toast.parentNode) toast.remove(); }, 2500);
    }

    // --------------------------------------------------------
    // OLD → NEW MODAL (shown on submit when dirty fields exist)
    // --------------------------------------------------------

    function showSanitizeModal(dirtyFields, onConfirm) {
        var existing = document.getElementById('xss-sanitize-overlay');
        if (existing) existing.remove();

        // Build one table row per dirty field
        var rows = dirtyFields.map(function (f) {
            var label = f.el.name || f.el.id || f.el.placeholder || 'Field';
            return (
                '<tr>' +
                  '<td style="padding:8px 10px;border:1px solid #ddd;font-weight:600;' +
                       'color:#555;font-size:12px;vertical-align:top;white-space:nowrap;">' +
                       escapeHtml(label) + '</td>' +
                  '<td style="padding:8px 10px;border:1px solid #ddd;color:#c0392b;' +
                       'word-break:break-all;font-size:13px;">' + escapeHtml(f.oldVal) + '</td>' +
                  '<td style="padding:8px 10px;border:1px solid #ddd;color:#27ae60;' +
                       'word-break:break-all;font-size:13px;">' + escapeHtml(f.newVal) + '</td>' +
                '</tr>'
            );
        }).join('');

        var html =
            '<div id="xss-sanitize-overlay" style="' +
                'position:fixed;top:0;left:0;width:100%;height:100%;' +
                'background:rgba(0,0,0,0.5);z-index:999998;' +
                'display:flex;align-items:center;justify-content:center;' +
                'font-family:Arial,sans-serif;">' +

              '<div style="background:#fff;border-radius:10px;padding:28px 32px;' +
                   'max-width:580px;width:92%;box-shadow:0 12px 40px rgba(0,0,0,0.25);' +
                   'max-height:90vh;overflow-y:auto;">' +

                // ── Header ──
                '<div style="display:flex;align-items:flex-start;gap:12px;margin-bottom:18px;">' +
                  '<span style="font-size:24px;line-height:1;">&#9888;&#65039;</span>' +
                  '<div>' +
                    '<div style="font-size:16px;font-weight:700;color:#333;">' +
                      'Special Characters Detected</div>' +
                    '<div style="font-size:12px;color:#888;margin-top:3px;">' +
                      'The following fields contain characters that will be replaced before saving.' +
                    '</div>' +
                  '</div>' +
                '</div>' +

                // ── Table ──
                '<table style="width:100%;border-collapse:collapse;margin-bottom:20px;">' +
                  '<thead>' +
                    '<tr style="background:#f5f5f5;">' +
                      '<th style="padding:8px 10px;text-align:left;border:1px solid #ddd;' +
                           'font-size:12px;color:#555;">Field</th>' +
                      '<th style="padding:8px 10px;text-align:left;border:1px solid #ddd;' +
                           'font-size:12px;color:#c0392b;">&#x2718; Old Value</th>' +
                      '<th style="padding:8px 10px;text-align:left;border:1px solid #ddd;' +
                           'font-size:12px;color:#27ae60;">&#x2714; New Value</th>' +
                    '</tr>' +
                  '</thead>' +
                  '<tbody>' + rows + '</tbody>' +
                '</table>' +

                // ── Note ──
                '<p style="font-size:12px;color:#777;margin:0 0 20px;line-height:1.6;">' +
                  'Click <strong>OK</strong> to save with the sanitized values, or ' +
                  '<strong>Cancel</strong> to go back and edit manually.' +
                '</p>' +

                // ── Buttons ──
                '<div style="display:flex;gap:10px;justify-content:flex-end;">' +
                  '<button id="xss-modal-cancel" style="padding:9px 22px;border:1px solid #ccc;' +
                    'border-radius:5px;background:#fff;cursor:pointer;font-size:14px;color:#333;">' +
                    'Cancel' +
                  '</button>' +
                  '<button id="xss-modal-ok" style="padding:9px 22px;background:#2980b9;' +
                    'color:#fff;border:none;border-radius:5px;cursor:pointer;font-size:14px;' +
                    'font-weight:600;">' +
                    'OK, Save Changes' +
                  '</button>' +
                '</div>' +

              '</div>' +
            '</div>';

        document.body.insertAdjacentHTML('beforeend', html);

        document.getElementById('xss-modal-ok').addEventListener('click', function () {
            document.getElementById('xss-sanitize-overlay').remove();
            onConfirm(true);
        });

        document.getElementById('xss-modal-cancel').addEventListener('click', function () {
            document.getElementById('xss-sanitize-overlay').remove();
            onConfirm(false);
        });

        // Dismiss on backdrop click
        document.getElementById('xss-sanitize-overlay').addEventListener('click', function (e) {
            if (e.target === this) {
                this.remove();
                onConfirm(false);
            }
        });
    }

    // --------------------------------------------------------
    // INPUT — REAL-TIME SANITIZATION (toast, no modal)
    // --------------------------------------------------------

    document.addEventListener('input', function (e) {
        var el = e.target;
        if (shouldSkip(el)) return;

        if (isTextField(el)) {
            var original = el.value;
            var cleaned  = sanitizeRealTime(original);
            if (cleaned !== original) el.value = cleaned;
        }

        if (isNumberField(el)) {
            el.value = sanitizeNumber(el.value);
        }
    }, true);

    // --------------------------------------------------------
    // PASTE — SANITIZATION (toast, no modal)
    // --------------------------------------------------------

    document.addEventListener('paste', function (e) {
        var el = e.target;
        if (shouldSkip(el)) return;

        var pasted  = (e.clipboardData || window.clipboardData).getData('text');
        var cleaned = sanitizeRealTime(pasted);

        if (cleaned !== pasted) {
            e.preventDefault();
            document.execCommand('insertText', false, cleaned);
        }
    }, true);

    // --------------------------------------------------------
    // SUBMIT — SHOW OLD→NEW MODAL FOR DIRTY FIELDS
    // --------------------------------------------------------

    document.addEventListener('submit', function (e) {
		alert(1236);
        var form = e.target;
        var dirtyFields = [];

        form.querySelectorAll('input, textarea').forEach(function (el) {
            if (shouldSkip(el)) return;

            if (isTextField(el)) {
                var oldVal = el.value;
                if (hasSpecialChars(oldVal)) {
                    dirtyFields.push({ el: el, oldVal: oldVal, newVal: applySanitize(oldVal) });
                }
            }

            if (isNumberField(el)) {
                el.value = sanitizeNumber(el.value);
            }
        });

        // No dirty fields — let the form submit normally
        if (dirtyFields.length === 0) return;

        // Dirty fields found — intercept and show modal
        e.preventDefault();
        e.stopImmediatePropagation();

        showSanitizeModal(dirtyFields, function (confirmed) {
            if (confirmed) {
                dirtyFields.forEach(function (f) { f.el.value = f.newVal; });
                form.submit(); // re-submit with clean values
            }
            // Cancelled: do nothing — user can edit manually
        });

    }, true);

    console.log('✅ Enterprise XSS Protection + Sanitize Modal Loaded');

})();
