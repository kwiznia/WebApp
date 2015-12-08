$(document).ready(function() {
	$(function() {
		$("#search").autocomplete({

			source : function(request, response) {
				$.ajax({
					url : "SearchController",
					type : "POST",
					data : {
						term : request.term
					},
					dataType : "json",
					success : function(data) {
						response(data);
					}
				});
			}
		});
	});
});
