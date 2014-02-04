String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo))
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")),
				replaceWith);
	else
		return this.replace(reallyDo, replaceWith);
};

String.prototype.charLength = function() {
	var len = 0;
	for (var i = 0; i < this.length; i++) {
		var c = this.charCodeAt(i);
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f))
			len += 1;
		else
			len += 2;
	}
	return len;
};
