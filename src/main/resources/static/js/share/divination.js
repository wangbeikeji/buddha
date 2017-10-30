function initialize() {
			var it = 0;
			var mytext;
			show($("#text1"), it, mytext, function() {				
				show($("#text2"), it, mytext, function() {				
					show($("#text3"), it, mytext, function() {						
						show($("#text4"), it, mytext, function() {							
							show($("#text5"), it, mytext, function() {							
								show($("#text6"), it, mytext, function() {					
								})
							})
						})
					})
				})
			});
		}
		function show(obj, it, mytext, _function) {
			obj.parent().show()
			mytext = obj.text();
			var myheight = obj.offsetHeight;
			obj.text("");
			obj.css("height", myheight);
			obj.show();
			typeit(obj, it, mytext, _function);
		}
		var t;

		function typeit(obj, it, mytext, _function) {
			obj.append(mytext.charAt(it));
			if(it < mytext.length - 1) {
				it++
				t = setTimeout(function() {
					typeit(obj, it, mytext, _function)
				}, 50);
			} else {
				clearTimeout(t);
				it = 0,
					mytext;
				_function();
			}
		}
		$(document).ready(function() {
			initialize();
		});