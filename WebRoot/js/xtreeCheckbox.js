/*----------------------------------------------------------------------------\
|                       Cross Browser Tree Widget 1.17                        |
|-----------------------------------------------------------------------------|
|                          Created by Emil A Eklund                           |
|                  (http://webfx.eae.net/contact.html#emil)                   |
|                      For WebFX (http://webfx.eae.net/)                      |
|-----------------------------------------------------------------------------|
| An object based tree widget,  emulating the one found in microsoft windows, |
| with persistence using cookies. Works in IE 5+, Mozilla and konqueror 3.    |
|-----------------------------------------------------------------------------|
|                   Copyright (c) 1999 - 2002 Emil A Eklund                   |
|-----------------------------------------------------------------------------|
| This software is provided "as is", without warranty of any kind, express or |
| implied, including  but not limited  to the warranties of  merchantability, |
| fitness for a particular purpose and noninfringement. In no event shall the |
| authors or  copyright  holders be  liable for any claim,  damages or  other |
| liability, whether  in an  action of  contract, tort  or otherwise, arising |
| from,  out of  or in  connection with  the software or  the  use  or  other |
| dealings in the software.                                                   |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| This  software is  available under the  three different licenses  mentioned |
| below.  To use this software you must chose, and qualify, for one of those. |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| The WebFX Non-Commercial License          http://webfx.eae.net/license.html |
| Permits  anyone the right to use the  software in a  non-commercial context |
| free of charge.                                                             |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| The WebFX Commercial license           http://webfx.eae.net/commercial.html |
| Permits the  license holder the right to use  the software in a  commercial |
| context. Such license must be specifically obtained, however it's valid for |
| any number of  implementations of the licensed software.                    |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| GPL - The GNU General Public License    http://www.gnu.org/licenses/gpl.txt |
| Permits anyone the right to use and modify the software without limitations |
| as long as proper  credits are given  and the original  and modified source |
| code are included. Requires  that the final product, software derivate from |
| the original  source or any  software  utilizing a GPL  component, such  as |
| this, is also licensed under the GPL license.                               |
|-----------------------------------------------------------------------------|
| Dependencies: xtree.css (To set up the CSS of the tree classes)             |
|-----------------------------------------------------------------------------|
| 2001-01-10 | Original Version Posted.                                       |
| 2001-03-18 | Added getSelected and get/setBehavior  that can make it behave |
|            | more like windows explorer, check usage for more information.  |
| 2001-09-23 | Version 1.1 - New features included  keyboard  navigation (ie) |
|            | and the ability  to add and  remove nodes dynamically and some |
|            | other small tweaks and fixes.                                  |
| 2002-01-27 | Version 1.11 - Bug fixes and improved mozilla support.         |
| 2002-06-11 | Version 1.12 - Fixed a bug that prevented the indentation line |
|            | from  updating correctly  under some  circumstances.  This bug |
|            | happened when removing the last item in a subtree and items in |
|            | siblings to the remove subtree where not correctly updated.    |
| 2002-06-13 | Fixed a few minor bugs cased by the 1.12 bug-fix.              |
| 2002-08-20 | Added usePersistence flag to allow disable of cookies.         |
| 2002-10-23 | (1.14) Fixed a plus icon issue                                 |
| 2002-10-29 | (1.15) Last changes broke more than they fixed. This version   |
|            | is based on 1.13 and fixes the bugs 1.14 fixed withou breaking |
|            | lots of other things.                                          |
| 2003-02-15 | The  selected node can now be made visible even when  the tree |
|            | control  loses focus.  It uses a new class  declaration in the |
|            | css file '.webfx-tree-item a.selected-inactive', by default it |
|            | puts a light-gray rectangle around the selected node.          |
| 2003-03-16 | Adding target support after lots of lobbying...                |
|-----------------------------------------------------------------------------|
| Created 2000-12-11 | All changes are in the log above. | Updated 2003-03-16 |
\----------------------------------------------------------------------------*/
 
var webFXTreeConfig = {
	rootIcon        : '../images/forTree/foldericon.gif',
	openRootIcon    : '../images/forTree/openfoldericon.gif',
	folderIcon      : '../images/forTree/foldericon.gif',
	openFolderIcon  : '../images/forTree/openfoldericon.gif',
	dutyIcon        : '../images/forTree/folder_1.gif',
	openDutyIcon    : '../images/forTree/folder_2.png',
	Icon_Normal     : '../images/forTree/NormalFolder_1.gif',
	OpenIcon_Normal : '../images/forTree/NormalFolder_2.gif',
	fileIcon        : '../images/forTree/folder_1.gif',
	iIcon           : '../images/forTree/I.png',
	lIcon           : '../images/forTree/Tplus.png',
	lMinusIcon      : '../images/forTree/Lminus.png',
	lPlusIcon       : '../images/forTree/Lplus.png',
	tIcon           : '../images/forTree/Tplus.png',
	tMinusIcon      : '../images/forTree/Tminus.png',
	tPlusIcon       : '../images/forTree/Tplus.png',
	blankIcon       : '../images/forTree/blank.png',
	leafIcon        : '../images/forTree/T.png',
	defaultText     : 'Tree Item',
	defaultAction   : 'javascript:void(0);',
	defaultBehavior : 'classic',
	usePersistence	: false
};

var webFXTreeHandler = {
	idCounter : 0,
	idPrefix  : "webfx-tree-object-",
	all       : {},
	behavior  : null,
	selected  : null,
	onSelect  : null, /* should be part of tree, not handler */
	getId     : function() { return this.idPrefix + this.idCounter++; },
	dblClick  : function (oItem) { this.all[oItem.id.replace('-box','')].dblClick(); },
	click     : function (oItem) { this.all[oItem.id.replace('-box','')].click(); },
	toggle    : function (oItem) { this.all[oItem.id.replace('-plus','')].toggle(); },
	toggleAnchor    : function (oItem) { this.all[oItem.id.replace('-anchor','')].toggle(); },
	select    : function (oItem) { this.all[oItem.id.replace('-icon','')].select(); },
	focus     : function (oItem) { this.all[oItem.id.replace('-anchor','')].focus(); },
	blur      : function (oItem) { this.all[oItem.id.replace('-anchor','')].blur(); },
	keydown   : function (oItem, e) { return this.all[oItem.id].keydown(e.keyCode); },
	cookies   : new WebFXCookie(),
	insertHTMLBeforeEnd	:	function (oElement, sHTML) {
		if (oElement.insertAdjacentHTML != null) {
			oElement.insertAdjacentHTML("BeforeEnd", sHTML)
			return;
		}
		var df;	// DocumentFragment
		var r = oElement.ownerDocument.createRange();
		r.selectNodeContents(oElement);
		r.collapse(false);
		df = r.createContextualFragment(sHTML);
		oElement.appendChild(df);
	}
};

/*
 * WebFXCookie class
 */

function WebFXCookie() {
//	if (document.cookie.length) { this.cookies = ' ' + document.cookie; }
}

WebFXCookie.prototype.setCookie = function (key, value) {
//	document.cookie = key + "=" + escape(value);
}

WebFXCookie.prototype.getCookie = function (key) {
	if (this.cookies) {
		var start = this.cookies.indexOf(' ' + key + '=');
		if (start == -1) { return null; }
		var end = this.cookies.indexOf(";", start);
		if (end == -1) { end = this.cookies.length; }
		end -= start;
		var cookie = this.cookies.substr(start,end);
		return unescape(cookie.substr(cookie.indexOf('=') + 1, cookie.length - cookie.indexOf('=') + 1));
	}
	else { return null; }
}


/*
 * WebFXTreeAbstractNode class
 */

function WebFXTreeAbstractNode(sText, sAction,sNid,isDep,isNormal) {
	this.childNodes  = [];
	this.id   = sNid;
	this.isDpm = isDep;
	this.isNormal = isNormal;
//	this.id     = webFXTreeHandler.getId();
	this.text   = sText || webFXTreeConfig.defaultText;
	this.action = sAction || webFXTreeConfig.defaultAction;
	this._last  = false;
	this.checked = null;
	webFXTreeHandler.all[this.id] = this;
}

/*
 * To speed thing up if you're adding multiple nodes at once (after load)
 * use the bNoIdent parameter to prevent automatic re-indentation and call
 * the obj.ident() method manually once all nodes has been added.
 */


WebFXTreeAbstractNode.prototype.add = function (node, bNoIdent) {
	node.parentNode = this;
	this.childNodes[this.childNodes.length] = node;
	var root = this;
	if (this.childNodes.length >= 2) {
		this.childNodes[this.childNodes.length - 2]._last = false;
	}
	while (root.parentNode) { root = root.parentNode; }
	if (root.rendered) {
		if (this.childNodes.length >= 2) {
			document.getElementById(this.childNodes[this.childNodes.length - 2].id + '-plus').src = ((this.childNodes[this.childNodes.length -2].folder)?((this.childNodes[this.childNodes.length -2].open)?webFXTreeConfig.tMinusIcon:webFXTreeConfig.tPlusIcon):webFXTreeConfig.tIcon);
			this.childNodes[this.childNodes.length - 2].plusIcon = webFXTreeConfig.tPlusIcon;
			this.childNodes[this.childNodes.length - 2].minusIcon = webFXTreeConfig.tMinusIcon;
			this.childNodes[this.childNodes.length - 2]._last = false;
		}
		this._last = true;
		var foo = this;
		while (foo.parentNode) {
			for (var i = 0; i < foo.parentNode.childNodes.length; i++) {
				if (foo.id == foo.parentNode.childNodes[i].id) { break; }
			}
			if (i == foo.parentNode.childNodes.length - 1) { foo.parentNode._last = true; }
			else { foo.parentNode._last = false; }
			foo = foo.parentNode;
		}
		webFXTreeHandler.insertHTMLBeforeEnd(document.getElementById(this.id + '-cont'), node.toString());
		if ((!this.folder) && (!this.openIcon)) {
			this.icon = webFXTreeConfig.folderIcon;
			this.openIcon = webFXTreeConfig.openFolderIcon;
		}
		if (!this.folder) { this.folder = true; this.collapse(true); }
		if (!bNoIdent) { this.indent(); }
	}
	return node;
}


WebFXTreeAbstractNode.prototype.getDeptID = function() {
	if( this.id.substring(0,1) != "D"){
		return "";
	}
	var id = "";
	if (this.id != "D0" && this.checked)
	{
		id =  "," + this.id.substring(1);
	}
	
	var treeNode ;
	for (var i = 0; i < this.childNodes.length; i++) {
		treeNode = this.childNodes[i];
		if (treeNode){
			id = id + treeNode.getDeptID();
		}
	}
	return id;
}

WebFXTreeAbstractNode.prototype.getDeptText = function() {
	if( this.id.substring(0,1) != "D"){
		return "";
	}
	var id = "";
	if (this.id != "D0" && this.checked)
	{
		id =  "," + this.text;
	}
	
	var treeNode ;
	for (var i = 0; i < this.childNodes.length; i++) {
		treeNode = this.childNodes[i];
		if (treeNode){
			id = id + treeNode.getDeptText();
		}
	}
	return id;
}

WebFXTreeAbstractNode.prototype.getDutyID = function() {
	if( this.id.substring(0,1) == "P"){
		return "";
	}
	var id = "";
	if (this.id.substring(0,1) == "T" && this.checked)
	{
		id =  "," + this.id.substring(1);
		return id;
	}
	
	var treeNode ;
	for (var i = 0; i < this.childNodes.length; i++) {
		treeNode = this.childNodes[i];
		if (treeNode){
			id = id + treeNode.getDutyID();
		}
	}
	return id;
}

WebFXTreeAbstractNode.prototype.getDutyText = function() {
	if( this.id.substring(0,1) == "P"){
		return "";
	}
	var id = "";
	if (this.id.substring(0,1) == "T" && this.checked)
	{
		id =  "," + this.text;
		return id;
	}
	
	var treeNode ;
	for (var i = 0; i < this.childNodes.length; i++) {
		treeNode = this.childNodes[i];
		if (treeNode){
			id = id + treeNode.getDutyText();
		}
	}
	return id;
}

WebFXTreeAbstractNode.prototype.getPeopleID = function() {
	var id = "";
//	alert(this.id);
//	alert(this.checked);
	if (this.id.substring(0,1) == "P" && this.checked)
	{
		id =  "," + this.id.substring(1);
//		alert("ff" + id);
		return id;
	}
	
	var treeNode ;
	for (var i = 0; i < this.childNodes.length; i++) {
		treeNode = this.childNodes[i];
		if (treeNode){
			id = id + treeNode.getPeopleID();
		}
	}
//	alert(id);
	return id;
}

WebFXTreeAbstractNode.prototype.getPeopleText = function() {
	var id = "";
	if (this.id.substring(0,1) == "P" && this.checked)
	{
		id =  "," + this.text;
		return id;
	}
	
	var treeNode ;
	for (var i = 0; i < this.childNodes.length; i++) {
		treeNode = this.childNodes[i];
		if (treeNode){
			id = id + treeNode.getPeopleText();
		}
	}
	return id;
}

WebFXTreeAbstractNode.prototype.getTreeNodeByID = function(NodeID) {
	if( this.id.substring(1) == NodeID){
		return this;
	}
	var treeNode ;
	for (var i = 0; i < this.childNodes.length; i++) {
		treeNode = this.childNodes[i].getTreeNodeByID(NodeID);
		if (treeNode){
			return treeNode;
		}
	}
}

WebFXTreeAbstractNode.prototype.toggle = function() {
	if (this.folder) {
		if (this.open) { this.collapse(); }
		else { this.expand(); }
	}	
}

WebFXTreeAbstractNode.prototype.select = function() {
	document.getElementById(this.id + '-anchor').focus();
	//document.getElementById(this.id + '-radio').checked = true;
	//alert(document.getElementById(this.id + '-radio').checked);
}

WebFXTreeAbstractNode.prototype.deSelect = function() {
	document.getElementById(this.id + '-anchor').className = '';
	webFXTreeHandler.selected = null;
}
/*
WebFXTreeAbstractNode.prototype.changeName = function (Name) {
	this.text = Name;
}

*/


WebFXTreeAbstractNode.prototype.click = function() {
//		alert(this.checked);
	if(this.isSingle){
		if (!this.checked){
			var checkedNode = this.getTopParent().checkedNode;
			if (checkedNode){
				document.getElementById(checkedNode.id + "-box").checked = null;
				checkedNode.checked = null;
			}
//			this.getTopParent().deCheckAll_New();
			document.getElementById(this.id + "-box").checked = true;
			this.checked = true;
			this.getTopParent().checkedNode = this;
		}else{
			document.getElementById(this.id + "-box").checked = true;
		}
	}else{
		if (!this.checked){
			document.getElementById(this.id + "-box").checked = true;
			this.checked = true;
		}else{
			document.getElementById(this.id + "-box").checked = null;
			this.checked = null;
		}
	}

}

WebFXTreeAbstractNode.prototype.dblClick = function() {
	if (this.selType == "PEOPLE"){
		if(this.isSingle){
			if (!this.checked){
				this.getTopParent().deCheckAll();
				document.getElementById(this.id + "-box").checked = true;
				this.checked = true;
				this.checkParent();
			}
		}else{
			if (this.checked){
				document.getElementById(this.id + "-box").checked = null;
				this.checked = null;
				this.deCheckChild();
//				this.deCheckParent();
			}else{
				document.getElementById(this.id + "-box").checked = true;
				this.checked = true;
				this.checkChild();
//				this.checkParent();
			}
		}
	}else if(this.selType == "DUTY"){
		if(this.isSingle){
			if(!this.checked){
				this.getTopParent().deCheckAllReal();
				document.getElementById(this.id + "-box").checked = true;
				this.checked = true;
			}
		}else{
			if(!this.checked){
				document.getElementById(this.id + "-box").checked = true;
				this.checked = true;
			}
		
		}
	}else{
		if(this.isSingle){
			if(!this.checked){
				this.getTopParent().deCheckAllReal();
				document.getElementById(this.id + "-box").checked = true;
				this.checked = true;
			}
		}else{
			if(!this.checked){
				document.getElementById(this.id + "-box").checked = true;
				this.checked = true;
			}
		
		}
	}
}

WebFXTreeAbstractNode.prototype.checkChild = function() {
	var treeNode ;
	for (var i = 0; i < this.childNodes.length; i++) {
		treeNode =  this.childNodes[i];
		if(treeNode){
			document.getElementById(treeNode.id + "-box").checked = true;
			treeNode.checked = true;
			treeNode.checkChild();
		}
	}
}


WebFXTreeAbstractNode.prototype.deCheckAll_New = function() {
	var treeNode ;
	for (var i = 0; i < webFXTreeHandler.all.length; i++) {
		treeNode =  webFXTreeHandler.all[i];
		if(treeNode){
			document.getElementById(treeNode.id + "-box").checked = null;
			treeNode.checked = null;
			treeNode.deCheckAll_New();
		}
	}
}

WebFXTreeAbstractNode.prototype.deCheckChild = function() {
	var treeNode ;
	for (var i = 0; i < this.childNodes.length; i++) {
		treeNode =  this.childNodes[i];
		if(treeNode){
			document.getElementById(treeNode.id + "-box").checked = null;
			treeNode.checked = null;
			treeNode.deCheckChild();
		}
	}
}

WebFXTreeAbstractNode.prototype.checkParent = function() {
	var treeNode = this.getParent();
	if (treeNode)
	{
		if (treeNode.checked)
		{
			return;
		}else{
			document.getElementById(treeNode.id + "-box").checked = true;
			treeNode.checked = true;
			treeNode.checkParent();
		}
	}
}

WebFXTreeAbstractNode.prototype.deCheckParent = function() {
	var treeNode = this.getParent();
	if (treeNode)
	{
		if (treeNode.hasChildChecked())
		{
			return;
		}else{
			document.getElementById(treeNode.id + "-box").checked = null;
			treeNode.checked = null;
			treeNode.deCheckParent();
		}
	}
}

WebFXTreeAbstractNode.prototype.deCheckAll = function() {
	if(this.checked){
		var treeNode;
		this.checked = null;
		document.getElementById(this.id + "-box").checked = null;
		for(var i = 0; i < this.childNodes.length; i ++){
			treeNode = this.childNodes[i];
			if(treeNode){
				if(treeNode.checked){
					treeNode.deCheckAll();
					break;
				}

			}
		}
	}
}


WebFXTreeAbstractNode.prototype.deCheckAllReal = function() {
	if(this.checked){
		this.checked = null;
		document.getElementById(this.id + "-box").checked = null;
	}
	var treeNode;
	for(var i = 0; i < this.childNodes.length; i ++){
		treeNode = this.childNodes[i];
		if(treeNode){
			treeNode.deCheckAllReal();
		}
	}
}

WebFXTreeAbstractNode.prototype.getTopParent = function() {
	if(this.getParent()){
		return this.getParent().getTopParent();
	}else{
		return this;
	}
}


WebFXTreeAbstractNode.prototype.expandNode = function() {
	var parentNode = this.getParent();
	if(parentNode != null) parentNode.expandNode();
    this.expand(); 
}

WebFXTreeAbstractNode.prototype.disabled = function (disFlag) {
	if(disFlag == "DEPT"){
		if(this.id.substring(0,1) == "D"){
			document.getElementById(this.id + "-box").disabled = true;
			var treeNode ;
			for(var i = 0; i < this.childNodes.length; i ++){
				treeNode = this.childNodes[i];
				if(treeNode){
					treeNode.disabled(disFlag);
				}
			}
		}
	}else if(disFlag == "DUTY"){
		if(this.id.substring(0,1) == "D" ||this.id.substring(0,1) == "T" ){
			document.getElementById(this.id + "-box").disabled = true;
			var treeNode ;
			for(var i = 0; i < this.childNodes.length; i ++){
				treeNode = this.childNodes[i];
				if(treeNode){
					treeNode.disabled(disFlag);
				}
			}
		}
	}
}

WebFXTreeAbstractNode.prototype.hasChildChecked = function() {
	var treeNode ;
	for (var i = 0; i < this.childNodes.length; i++) {
		treeNode =  this.childNodes[i];
		if(treeNode){
			if (treeNode.checked)
			{
				return true;					
			}
		}
	}
	return false;
}

WebFXTreeAbstractNode.prototype.getPeopleCheckID = function() {
	var checkIDStr = "";
	if (this.checked){
		if ( !this.folder){
			if(this.id.substring(0,1) == "P")checkIDStr = this.id.substring(1) + ",";
			return checkIDStr;
		}else{
			var treeNode ;
			for (var i = 0; i < this.childNodes.length; i++) {
				treeNode =  this.childNodes[i];
				if(treeNode){
					checkIDStr = checkIDStr + treeNode.getPeopleCheckID();
				}
			}
		}
	}
	return checkIDStr;
}

WebFXTreeAbstractNode.prototype.getCheckID = function() {
	var checkIDStr = "";
	if (this.checked)checkIDStr = this.id.substring(1) + ",";
	if ( this.folder){
		var treeNode ;
		for (var i = 0; i < this.childNodes.length; i++) {
			treeNode =  this.childNodes[i];
			if(treeNode){
				checkIDStr = checkIDStr + treeNode.getCheckID();
			}
		}
	}
	return checkIDStr;
}

WebFXTreeAbstractNode.prototype.getCheckPeopleText = function() {
	var checkTextStr = "";
	if (this.checked){
		if ( !this.folder){
			if(this.id.substring(0,1) == "P")checkTextStr = "(" +  this.getParent().getParent().text +  this.getParent().text + ")" + this.text + ",";
			return checkTextStr;
		}else{
			var treeNode ;
			for (var i = 0; i < this.childNodes.length; i++) {
				treeNode =  this.childNodes[i];
				if(treeNode){
					checkTextStr = checkTextStr + treeNode.getCheckPeopleText();
				}
			}
		}
	}
	return checkTextStr;
}

WebFXTreeAbstractNode.prototype.getCheckDeptText = function() {
	var checkTextStr = "";
	if (this.checked && this.id.substring(0,1) == "D")checkTextStr = this.text + ",";
	var treeNode ;
	for (var i = 0; i < this.childNodes.length; i++) {
		treeNode =  this.childNodes[i];
		if(treeNode){
			checkTextStr = checkTextStr + treeNode.getCheckDeptText();
		}
	}
	return checkTextStr;
}

WebFXTreeAbstractNode.prototype.getCheckDutyText = function() {
	var checkTextStr = "";
	if (this.checked && this.id.substring(0,1) == "T")checkTextStr = "(" + this.getParent().text + ")"  + this.text + ",";
	if ( this.folder){
		var treeNode ;
		for (var i = 0; i < this.childNodes.length; i++) {
			treeNode =  this.childNodes[i];
			if(treeNode){
				checkTextStr = checkTextStr + treeNode.getCheckDutyText();
			}
		}
	}
	return checkTextStr;
}


WebFXTreeAbstractNode.prototype.getLeafNodeByID = function(id) {
	var leaf = null;
	if ( !this.folder ){
		if (this.id == id){
			return this;
		}
		return null;
	}else{
		var treeNode ;		
		for (var i = 0; i < this.childNodes.length; i++) {
			treeNode =  this.childNodes[i];
			leaf = treeNode.getLeafNodeByID(id);
			if(leaf) return leaf;
		}
	}
	return leaf;
}

WebFXTreeAbstractNode.prototype.focus = function() {
	if ((webFXTreeHandler.selected) && (webFXTreeHandler.selected != this)) { webFXTreeHandler.selected.deSelect(); }
//	webFXTreeHandler.selected.deSelect(); 
	if (webFXTreeHandler.selected != this)
	{
		webFXTreeHandler.selected = this;
		if ((this.openIcon) && (webFXTreeHandler.behavior != 'classic')) { document.getElementById(this.id + '-icon').src = this.openIcon; }
		document.getElementById(this.id + '-anchor').className = 'selected';
		document.getElementById(this.id + '-anchor').focus();
		if (webFXTreeHandler.onSelect) { webFXTreeHandler.onSelect(this); }
	}
}

WebFXTreeAbstractNode.prototype.blur = function() {
	if ((this.openIcon) && (webFXTreeHandler.behavior != 'classic')) { document.getElementById(this.id + '-icon').src = this.icon; }
	document.getElementById(this.id + '-anchor').className = 'selected-inactive';
}

WebFXTreeAbstractNode.prototype.doExpand = function() {
	if (webFXTreeHandler.behavior == 'classic') { document.getElementById(this.id + '-icon').src = this.openIcon; }
	if (this.childNodes.length) {  document.getElementById(this.id + '-cont').style.display = 'block'; }
	this.open = true;
//	if (webFXTreeConfig.usePersistence) {
//		webFXTreeHandler.cookies.setCookie(this.id, '1');
//	}	
}

WebFXTreeAbstractNode.prototype.doCollapse = function() {
	if (webFXTreeHandler.behavior == 'classic') { document.getElementById(this.id + '-icon').src = this.icon; }
	if (this.childNodes.length) { document.getElementById(this.id + '-cont').style.display = 'none'; }
	this.open = false;
//	if (webFXTreeConfig.usePersistence) {
//		webFXTreeHandler.cookies.setCookie(this.id, '0');
//	}	
}

WebFXTreeAbstractNode.prototype.expandAll = function() {
	this.expandChildren();
	if ((this.folder) && (!this.open)) { this.expand(); }
}

WebFXTreeAbstractNode.prototype.expandChildren = function() {
	for (var i = 0; i < this.childNodes.length; i++) {
		this.childNodes[i].expandAll();
	} 
}

WebFXTreeAbstractNode.prototype.collapseAll = function() {
	this.collapseChildren();
	if ((this.folder) && (this.open)) { this.collapse(true); }
}

WebFXTreeAbstractNode.prototype.collapseChildren = function() {
	for (var i = 0; i < this.childNodes.length; i++) {
		this.childNodes[i].collapseAll();
	} 
}

WebFXTreeAbstractNode.prototype.indent = function(lvl, del, last, level, nodesLeft) {
	/*
	 * Since we only want to modify items one level below ourself,
	 * and since the rightmost indentation position is occupied by
	 * the plus icon we set this to -2
	 */
	if (lvl == null) { lvl = -2; }
	var state = 0;
	for (var i = this.childNodes.length - 1; i >= 0 ; i--) {
		state = this.childNodes[i].indent(lvl + 1, del, last, level);
		if (state) { return; }
	}
	if (del) {
		if ((level >= this._level) && (document.getElementById(this.id + '-plus'))) {
			if (this.folder) {
				document.getElementById(this.id + '-plus').src = (this.open)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.lPlusIcon;
				this.plusIcon = webFXTreeConfig.lPlusIcon;
				this.minusIcon = webFXTreeConfig.lMinusIcon;
			}
			else if (nodesLeft) { document.getElementById(this.id + '-plus').src = webFXTreeConfig.lIcon; }
			return 1;
	}	}
	var foo = document.getElementById(this.id + '-indent-' + lvl);
	if (foo) {
		if ((foo._last) || ((del) && (last))) { foo.src =  webFXTreeConfig.blankIcon; }
		else { foo.src =  webFXTreeConfig.iIcon; }
	}
	return 0;
}

/*
 * WebFXTree class
 */

function WebFXTree(sText, sAction, sBehavior, sIcon, sOpenIcon, sNid, isDep,isNormal) {
	this.base = WebFXTreeAbstractNode;
	this.base(sText, sAction,sNid, isDep,isNormal);
	this.icon      = sIcon || webFXTreeConfig.rootIcon;
	this.openIcon  = sOpenIcon || webFXTreeConfig.openRootIcon;
	/* Defaults to open */
	if (webFXTreeConfig.usePersistence) {
		this.open  = (webFXTreeHandler.cookies.getCookie(this.id) == '0')?false:true;
	} else { this.open  = true; }
	this.folder    = true;
	this.rendered  = false;
	this.onSelect  = null;
	this.checkedNode = null;
	if (!webFXTreeHandler.behavior) {  webFXTreeHandler.behavior = sBehavior || webFXTreeConfig.defaultBehavior; }
}

WebFXTree.prototype = new WebFXTreeAbstractNode;

WebFXTree.prototype.setBehavior = function (sBehavior) {
	webFXTreeHandler.behavior =  sBehavior;
};

WebFXTree.prototype.changeName = function (Name) {
	document.getElementById(this.id + "-anchor" ).innerHTML = Name;
//	alert(this);
}

WebFXTree.prototype.checkThisNode = function (id) {
	var i;
	if( document.all.proceeding == null) return "";
	for (i = 0; i < document.all.proceeding.length; i++){
		if (document.all.proceeding[i].value == id){
			document.all.proceeding[i].checked = true;
		}
	}
}


WebFXTree.prototype.getBehavior = function (sBehavior) {
	return webFXTreeHandler.behavior;
};

WebFXTree.prototype.getSelected = function() {
	if (webFXTreeHandler.selected) { return webFXTreeHandler.selected; }
	else { return null; }
}

WebFXTree.prototype.remove = function() { }

WebFXTree.prototype.expand = function() {
	this.doExpand();
}

WebFXTree.prototype.selThisNode = function(nodeId) {
	var thisNode = this.getNodeID(nodeId);
	if (thisNode)
	{
		if(thisNode.getParent() != null){
			thisNode.expandNode();
		}
	}
}

WebFXTree.prototype.collapse = function(b) {
	if (!b) { this.focus(); }
	this.doCollapse();
}

WebFXTree.prototype.getFirst = function() {
	return null;
}

WebFXTree.prototype.getLast = function() {
	return null;
}

WebFXTree.prototype.getParent = function() {
	return null;
}

WebFXTree.prototype.getNextSibling = function() {
	return null;
}

WebFXTree.prototype.getPreviousSibling = function() {
	return null;
}


WebFXTree.prototype.keydown = function(key) {
	if (key == 39) {
		if (!this.open) { this.expand(); }
		else if (this.childNodes.length) { this.childNodes[0].select(); }
		return false;
	}
	if (key == 37) { this.collapse(); return false; }
	if ((key == 40) && (this.open) && (this.childNodes.length)) { this.childNodes[0].select(); return false; }
	return true;
}

WebFXTree.prototype.getNodeID = function(NodeID) {
	if( this.id == NodeID){
		return this;
	}
	var treeNode ;
	for (var i = 0; i < this.childNodes.length; i++) {
		treeNode = this.childNodes[i].getNodeByID(NodeID);
		if (treeNode){
			return treeNode;
		}
	}
}



WebFXTree.prototype.toString = function() {
	var str = "<div id=\"" + this.id + "\"  class=\"webfx-tree-item\" >" +
		"<img id=\"" + this.id + "-icon\" class=\"webfx-tree-icon\" src=\"" + ((webFXTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"webFXTreeHandler.select(this);\">" +
		 ( (this.isDpm == '0')? "<input type=\"checkbox\"  name =\"proceeding\"" + " value=\"" + this.id + "\" >":"") +
		"<a     id=\"" + this.id + "-anchor\" onfocus=\"webFXTreeHandler.focus(this);\" onblur=\"webFXTreeHandler.blur(this);\"" +
		">" + this.text + "</a></div>" +
		"<div id=\"" + this.id + "-cont\" class=\"webfx-tree-container\" style=\"display: " + ((this.open)?'block':'none') + ";\">";
	var sb = [];
	for (var i = 0; i < this.childNodes.length; i++) {
		sb[i] = this.childNodes[i].toString(i, this.childNodes.length);
	}
	this.rendered = true;	
	return str + sb.join("") + "</div>";
}

WebFXTreeAbstractNode.prototype.expandAll = function(deep) {
	if (!deep || deep >0){
		this.expandChildren(deep - 1);
	}
	if ((this.folder) && (!this.open)) { this.expand(); }
}

WebFXTreeAbstractNode.prototype.expandChildren = function(deep) {
	for (var i = 0; i < this.childNodes.length; i++) {
		this.childNodes[i].expandAll(deep);
	} 
}


/*
 * WebFXTreeItem class
 */

function WebFXTreeItem(sText, sAction,sNid, isDep,isNormal, sIcon, sOpenIcon, eParent) {
	this.base = WebFXTreeAbstractNode;
	this.base(sText, sAction,sNid, isDep,isNormal);
	/* Defaults to close */
	if (webFXTreeConfig.usePersistence) {
		this.open = (webFXTreeHandler.cookies.getCookie(this.id) == '1')?true:false;
	} else { this.open = false; }
	if (sIcon) { this.icon = sIcon; }
	if (sOpenIcon) { this.openIcon = sOpenIcon; }
	if (eParent) { eParent.add(this); }
}

WebFXTreeItem.prototype = new WebFXTreeAbstractNode;

WebFXTreeItem.prototype.remove = function() {
	var iconSrc = document.getElementById(this.id + '-plus').src;
	var parentNode = this.parentNode;
	var prevSibling = this.getPreviousSibling(true);
	var nextSibling = this.getNextSibling(true);
	var folder = this.parentNode.folder;
	var last = ((nextSibling) && (nextSibling.parentNode) && (nextSibling.parentNode.id == parentNode.id))?false:true;
	this.getPreviousSibling().focus();
	this._remove();
	if (parentNode.childNodes.length == 0) {
		document.getElementById(parentNode.id + '-cont').style.display = 'none';
		parentNode.doCollapse();
		parentNode.folder = false;
		parentNode.open = false;
	}
	if (!nextSibling || last) { parentNode.indent(null, true, last, this._level, parentNode.childNodes.length); }
	if ((prevSibling == parentNode) && !(parentNode.childNodes.length)) {
		prevSibling.folder = false;
		prevSibling.open = false;
		iconSrc = document.getElementById(prevSibling.id + '-plus').src;
		iconSrc = iconSrc.replace('minus', '').replace('plus', '');
		document.getElementById(prevSibling.id + '-plus').src = iconSrc;
//		document.getElementById(prevSibling.id + '-icon').src = webFXTreeConfig.fileIcon;
	}
	if (document.getElementById(prevSibling.id + '-plus')) {
		if (parentNode == prevSibling.parentNode) {
			iconSrc = iconSrc.replace('minus', '').replace('plus', '');
			document.getElementById(prevSibling.id + '-plus').src = iconSrc;
}	}	}

WebFXTreeItem.prototype._remove = function() {
	for (var i = this.childNodes.length - 1; i >= 0; i--) {
		this.childNodes[i]._remove();
 	}
	for (var i = 0; i < this.parentNode.childNodes.length; i++) {
		if (this == this.parentNode.childNodes[i]) {
			for (var j = i; j < this.parentNode.childNodes.length; j++) {
				this.parentNode.childNodes[j] = this.parentNode.childNodes[j+1];
			}
			this.parentNode.childNodes.length -= 1;
			if (i + 1 == this.parentNode.childNodes.length) { this.parentNode._last = true; }
			break;
	}	}
	webFXTreeHandler.all[this.id] = null;
	var tmp = document.getElementById(this.id);
	if (tmp) { tmp.parentNode.removeChild(tmp); }
	tmp = document.getElementById(this.id + '-cont');
	if (tmp) { tmp.parentNode.removeChild(tmp); }
}

WebFXTreeItem.prototype.expand = function() {
	this.doExpand();
	document.getElementById(this.id + '-plus').src = this.minusIcon;
}

WebFXTreeItem.prototype.collapse = function(b) {
	if (!b) { this.focus(); }
	this.doCollapse();
	document.getElementById(this.id + '-plus').src = this.plusIcon;
}

WebFXTreeItem.prototype.getFirst = function() {
	return this.childNodes[0];
}

WebFXTreeItem.prototype.getLast = function() {
	if (this.childNodes[this.childNodes.length - 1].open) { return this.childNodes[this.childNodes.length - 1].getLast(); }
	else { return this.childNodes[this.childNodes.length - 1]; }
}

WebFXTreeItem.prototype.getNextSibling = function() {
	for (var i = 0; i < this.parentNode.childNodes.length; i++) {
		if (this == this.parentNode.childNodes[i]) { break; }
	}
	if (++i == this.parentNode.childNodes.length) { return this.parentNode.getNextSibling(); }
	else { return this.parentNode.childNodes[i]; }
}

WebFXTreeItem.prototype.getPreviousSibling = function(b) {
	for (var i = 0; i < this.parentNode.childNodes.length; i++) {
		if (this == this.parentNode.childNodes[i]) { break; }
	}
	if (i == 0) { return this.parentNode; }
	else {
		if ((this.parentNode.childNodes[--i].open) || (b && this.parentNode.childNodes[i].folder)) { return this.parentNode.childNodes[i].getLast(); }
		else { return this.parentNode.childNodes[i]; }
} }

WebFXTreeItem.prototype.keydown = function(key) {
	if ((key == 39) && (this.folder)) {
		if (!this.open) { this.expand(); }
		else { this.getFirst().select(); }
		return false;
	}
	else if (key == 37) {
		if (this.open) { this.collapse(); }
		else { this.parentNode.select(); }
		return false;
	}
	else if (key == 40) {
		if (this.open) { this.getFirst().select(); }
		else {
			var sib = this.getNextSibling();
			if (sib) { sib.select(); }
		}
		return false;
	}
	else if (key == 38) { this.getPreviousSibling().select(); return false; }
	return true;
}

WebFXTreeItem.prototype.getParent = function () {
	return this.parentNode;
}

WebFXTreeItem.prototype.toString = function (nItem, nItemCount) {
	var foo = this.parentNode;
	var indent = '';
	if (nItem + 1 == nItemCount) { this.parentNode._last = true; }
	var i = 0;
	while (foo.parentNode) {
		foo = foo.parentNode;
		indent = "<img id=\"" + this.id + "-indent-" + i + "\" src=\"" + ((foo._last)?webFXTreeConfig.blankIcon:webFXTreeConfig.iIcon) + "\">" + indent;
		i++;
	}
	this._level = i;
	if (this.childNodes.length) { this.folder = 1; }
	else { this.open = false; }
	if ((this.folder) || (webFXTreeHandler.behavior != 'classic')) {
		if (!this.icon) { this.icon = webFXTreeConfig.folderIcon; }
		if (!this.openIcon) { this.openIcon = webFXTreeConfig.openFolderIcon; }
	}
	else if (!this.icon) { this.icon = webFXTreeConfig.fileIcon; }

	if (this.isDpm == '1'){
		this.icon = webFXTreeConfig.folderIcon; 
		this.openIcon = webFXTreeConfig.openFolderIcon;
	}else if(this.isDpm == '0'){
		if(this.isNormal == '0'){
			this.icon = webFXTreeConfig.dutyIcon; 
			this.openIcon = webFXTreeConfig.openDutyIcon;
		}else{
			this.icon = webFXTreeConfig.Icon_Normal; 
			this.openIcon = webFXTreeConfig.OpenIcon_Normal;
		}
	}else{
		this.icon = webFXTreeConfig.fileIcon;
		this.openIcon = webFXTreeConfig.fileIcon;
	}
	var label = this.text.replace(/</g, '&lt;').replace(/>/g, '&gt;');
	var str = "<div id=\"" + this.id + "\"  class=\"webfx-tree-item\" >" +
		indent +
		"<img id=\"" + this.id + "-plus\" src=\"" + ((this.folder)?((this.open)?((this.parentNode._last)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.tMinusIcon):((this.parentNode._last)?webFXTreeConfig.lPlusIcon:webFXTreeConfig.tPlusIcon)):((this.parentNode._last)?webFXTreeConfig.lIcon:webFXTreeConfig.tIcon)) + "\" onclick=\"expandMenu('" + this.id + "');\">" +
		"<img id=\"" + this.id + "-icon\" class=\"webfx-tree-icon\" src=\"" + ((webFXTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"webFXTreeHandler.select(this);\">" +
		 ( (this.isDpm == '0')? "<input type=\"checkbox\" onclick='checkChecked(this);' name =\"proceeding\" id=\"" + this.id + "-checkbox\"  value=\"" + this.id + "\">":"") +
		"<a  href=\"javascript:expandMenu('" + this.id + "');\"  id=\"" + this.id + "-anchor\" onfocus=\"webFXTreeHandler.focus(this);\" onblur=\"webFXTreeHandler.blur(this);\"" +
		">" + label + "</a></div>" +
		"<div id=\"" + this.id + "-cont\" class=\"webfx-tree-container\" style=\"display: " + ((this.open)?'block':'none') + ";\">";
	var sb = [];
	for (var i = 0; i < this.childNodes.length; i++) {
		sb[i] = this.childNodes[i].toString(i,this.childNodes.length);
	}
	this.plusIcon = ((this.parentNode._last)?webFXTreeConfig.lPlusIcon:webFXTreeConfig.tPlusIcon);
	this.minusIcon = ((this.parentNode._last)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.tMinusIcon);
	//alert(str + sb.join("") + "</div>");
	return str + sb.join("") + "</div>";
}

WebFXTreeItem.prototype.getNodeByID = function (NodeID) {
	if (this.id == NodeID){
		return this;
	}	
	var treeNode ;
	for (var i = 0; i < this.childNodes.length; i++) {
		treeNode =  this.childNodes[i].getNodeByID(NodeID);
		if (treeNode){
			return treeNode;
		}
	}
}


WebFXTreeItem.prototype.getChildrenID = function () {
	var strID ;
	strID = this.id;
	for (var i = 0; i < this.childNodes.length; i++) {
		strID = strID + "#" +  this.childNodes[i].getChildrenID();
	}
	return strID;
}

WebFXTreeItem.prototype.changeName = function (Name) {
	document.getElementById(this.id + "-anchor" ).innerHTML = Name;
//	alert(this);
}
