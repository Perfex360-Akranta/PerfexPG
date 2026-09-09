package com.akranta.tpm.model;

	
public class FunctionalLocn {
	//private List<FunctionalLocn> functionalLocn;
	public FunctionalLocn()
	{
		
	}
	
	private String menuNumber;
	private String parentNumber;
	private String menuName;
	private String menuCaption;
	private String menuLevel;
	private String menuSortNumber;
	private boolean isParent;
	private String formName;
	private String relatedFilter;
	private boolean filterNeed;
	private String originalId;
	private String elementId;
	private String parentId;
	private String tempParentId;
	private String displayCode;
	private String description;
	private String elementType;
	private boolean active;
	private String cellOrder;
	
	
	/*public List<FunctionalLocn> getFunctionalLocn() {
		return functionalLocn;
	}

	public void setFunctionalLocn(List<FunctionalLocn> functionalLocn) {
		this.functionalLocn = functionalLocn;
	}*/

	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getRelatedFilter() {
		return relatedFilter;
	}
	public void setRelatedFilter(String relatedFilter) {
		this.relatedFilter = relatedFilter;
	}
	public String getMenuNumber() {
		return menuNumber;
	}
	public void setMenuNumber(String menuNumber) {
		this.menuNumber = menuNumber;
	}
	public String getParentNumber() {
		return parentNumber;
	}
	public void setParentNumber(String parentNumber) {
		this.parentNumber = parentNumber;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuCaption() {
		return menuCaption;
	}
	public void setMenuCaption(String menuCaption) {
		this.menuCaption = menuCaption;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getMenuSortNumber() {
		return menuSortNumber;
	}
	public void setMenuSortNumber(String menuSortNumber) {
		this.menuSortNumber = menuSortNumber;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public boolean isParent() {
		return isParent;
	}
	/**
	 * @param filterNeeded the filterNeeded to set
	 */
	public void setFilterNeed(boolean filterNeed) {
		this.filterNeed = filterNeed;
	}
	/**
	 * @return the filterNeeded
	 */
	public boolean isFilterNeed() {
		return filterNeed;
	}
	public String getOriginalId() {
		return originalId;
	}
	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public String getParentId() {
		return parentId;
	}
	public String getTempParentId() {
		return tempParentId;
	}
	public void setTempParentId(String tempParentId) {
		this.tempParentId = tempParentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDisplayCode() {
		return displayCode;
	}
	public void setDisplayCode(String displayCode) {
		this.displayCode = displayCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCellOrder() {
		return cellOrder;
	}
	public void setCellOrder(String cellOrder) {
		this.cellOrder = cellOrder;
	}

}
