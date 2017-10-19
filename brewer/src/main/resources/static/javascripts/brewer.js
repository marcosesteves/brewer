$(function() {
	var decimal = $('.js-decimal'); // .js-decimal classe de marcação para acessar o elemento com javascript
	decimal.maskMoney();
	
	var plain = $('.js-plain');  // idem
	plain.maskMoney({ precision: 0 });
});
/*
$(function() {
	$('.js-status').bootstrapSwitch();
});
*/