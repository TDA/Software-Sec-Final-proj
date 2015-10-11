/**
 * Created by schandramouli on 10/5/15.
 */
// Closes the sidebar menu
// Scrolls to the selected menu item on the page
$(document)
		.ready(
				function() {
					$("#menu-close").click(function(e) {
						e.preventDefault();
						$("#sidebar-wrapper").toggleClass("active");
					});

					var elem = '&nbsp;<a href="register" class="customer btn btn-dark btn-lg">Customer</a>&nbsp;&nbsp;'
							+ '<a href="register" class="employee btn btn-dark btn-lg">Employee</a>';

					$('#meddelanden').popover({
						animation : true,
						content : elem,
						html : true
					});

					// Opens the sidebar menu
					$("#menu-toggle").click(function(e) {
						e.preventDefault();
						$("#sidebar-wrapper").toggleClass("active");
					});

					$('a[href*=#]:not([href=#])')
							.click(
									function() {
										if (location.pathname
												.replace(/^\//, '') == this.pathname
												.replace(/^\//, '')
												|| location.hostname == this.hostname) {

											var target = $(this.hash);
											target = target.length ? target
													: $('[name='
															+ this.hash
																	.slice(1)
															+ ']');
											if (target.length) {
												$('html, body')
														.animate(
																{
																	scrollTop : target
																			.offset().top
																}, 1000);
												return false;
											}
										}
									});
				});
