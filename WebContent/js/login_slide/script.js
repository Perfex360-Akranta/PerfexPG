// -----------------------------------------------------------------------------------
// http://wowslider.com/
// JavaScript Wow Slider is a free software that helps you easily generate delicious 
// slideshows with gorgeous transition effects, in a few clicks without writing a single line of code.
// Last updated: 2012-03-29
//
//***********************************************
// Obfuscated by Javascript Obfuscator
// http://javascript-source.com
//***********************************************
function ws_blinds(j, g, h) {
	var e = jQuery;
	var c = j.parts || 3;
	g.each(function(i) {
		if (i != j.startSlide) {
			e(this).hide()
		}
	});
	var b = e("<div></div>");
	b.css({
		position : "absolute",
		width : "100%",
		height : "100%",
		left : 0,
		top : 0,
		"z-index" : 8
	});
	h.append(b);
	var d = [];
	for ( var f = 0; f < c; f++) {
		d[f] = e("<div></div>")
	}
	var a = {
		position : "absolute",
		"z-index" : 2,
		"background-repeat" : "no-repeat",
		height : "100%",
		border : "none",
		margin : 0,
		top : 0,
		left : 0
	};
	e(d).each(
			function(i) {
				a.left = Math.round(100 * i / c) + "%";
				a.width = Math.round(100 * (i + 1) / c)
						- Math.round(100 * i / c) + "%";
				e(this).css(a);
				b.append(this);
				b.hide()
			});
	this.go = function(n, p, l) {
		var m = p > n ? 1 : 0;
		if (l) {
			if (l <= -1) {
				n = (p + 1) % g.length;
				m = 0
			} else {
				if (l >= 1) {
					n = (p - 1 + g.length) % g.length;
					m = 1
				} else {
					return -1
				}
			}
		}
		for ( var o = 0; o < d.length; o++) {
			d[o].stop(true, true)
		}
		function k(s, t) {
			var r = d[s];
			var i = g.get(n);
			r.css({
				"background-position" : Math.round(100 * c / (c - 1)) + "% 0",
				"background-image" : "url(" + i.src + ")"
			});
			r.animate({
				"background-position" : Math.round(100 * s / (c - 1)) + "% 0"
			}, (j.duration / (d.length + 1))
					* (m ? (d.length - s + 1) : (s + 2)), t)
		}
		function q() {
			g.hide();
			e(g.get(n)).show();
			b.hide();
			e(d).each(function() {
				e(this).css({
					"background-image" : "none"
				})
			})
		}
		b.show();
		for ( var o = 0; o < d.length; o++) {
			k(o, (!m && o == d.length - 1 || m && !o) ? q : null)
		}
		return n
	}
}
(function(b) {
	if (!document.defaultView || !document.defaultView.getComputedStyle) {
		//var d = b.curCSS;
		var d = b.css;
		//b.curCSS = function(g, e, h) {
		b.css = function(g, e, h) {
			if (e === "background-position") {
				e = "backgroundPosition"
			}
			if (e !== "backgroundPosition" || !g.currentStyle
					|| g.currentStyle[e]) {
				return d.apply(this, arguments)
			}
			var f = g.style;
			if (!h && f && f[e]) {
				return f[e]
			}
			return d(g, "backgroundPositionX", h) + " "
					+ d(g, "backgroundPositionY", h)
		}
	}
	var c = b.fn.animate;
	b.fn.animate = function(e) {
		if ("background-position" in e) {
			e.backgroundPosition = e["background-position"];
			delete e["background-position"]
		}
		if ("backgroundPosition" in e) {
			e.backgroundPosition = "(" + e.backgroundPosition
		}
		return c.apply(this, arguments)
	};
	function a(f) {
		f = f.replace(/left|top/g, "0px");
		f = f.replace(/right|bottom/g, "100%");
		f = f.replace(/([0-9\.]+)(\s|\)|$)/g, "$1px$2");
		var e = f.match(/(-?[0-9\.]+)(px|\%|em|pt)\s(-?[0-9\.]+)(px|\%|em|pt)/);
		return [ parseFloat(e[1], 10), e[2], parseFloat(e[3], 10), e[4] ]
	}
	b.fx.step.backgroundPosition = function(f) {
		if (!f.bgPosReady) {
			//var h = b.curCSS(f.elem, "backgroundPosition");
			var h = b.css(f.elem, "backgroundPosition");
			if (!h) {
				h = "0px 0px"
			}
			h = a(h);
			f.start = [ h[0], h[2] ];
			var e = a(f.end);
			f.end = [ e[0], e[2] ];
			f.unit = [ e[1], e[3] ];
			f.bgPosReady = true
		}
		var g = [];
		g[0] = ((f.end[0] - f.start[0]) * f.pos) + f.start[0] + f.unit[0];
		g[1] = ((f.end[1] - f.start[1]) * f.pos) + f.start[1] + f.unit[1];
		f.elem.style.backgroundPosition = g[0] + " " + g[1]
	}
})(jQuery);// -----------------------------------------------------------------------------------
// http://wowslider.com/
// JavaScript Wow Slider is a free software that helps you easily generate
// delicious
// slideshows with gorgeous transition effects, in a few clicks without writing
// a single line of code.
// Last updated: 2012-03-29
//
// ***********************************************
// Obfuscated by Javascript Obfuscator
// http://javascript-source.com
// ***********************************************
jQuery("#wowslider-container1").wowSlider({
	effect : "blinds",
	prev : "",
	next : "",
	duration : 20 * 100,
	delay : 80 * 100,
	outWidth : 640,
	outHeight : 360,
	width : 640,
	height : 360,
	autoPlay : true,
	stopOnHover : false,
	loop : false,
	bullets : true,
	caption : true,
	controls : true,
	logo : "engine1/loading.gif",
	images : 0
});