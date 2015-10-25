$(document).ready(function() {
  $('#pdfConvertor').click(function () {
    var pdf = new jsPDF('p', 'pt', 'letter');
    // source can be HTML-formatted string, or a reference
    // to an actual DOM element from which the text will be scraped.
    // This element needs to be the parent tag, and not the child
    // that needs printing
    var printable = $('#printable');
    source = printable[0];
    name = printable.attr('data-name') || 'Important';
    margins = {
      top: 80,
      bottom: 60,
      left: 60,
      width: 640
    };
    pdf.fromHTML(
      source, // HTML string or DOM elem ref.
      margins.left, // x coord
      margins.top, {// y coord
        'width': margins.width
      },
      function (dispose) {
        // callback
        pdf.save(name + '.pdf');
      }
      , margins);
  });
});