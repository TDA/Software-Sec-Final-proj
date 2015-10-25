/**
 * Created by schandramouli on 10/1/15.
 */

var dict = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 0, 1, 2, 3, 4, 5, 6, 7, 8, 9];

function rn(x) {
  var rand = f(r() * (12 + 12 + 13 + 12 + 11 + 3));
  // console.log(rand.toString());
  // console.log(rand.tS());
  return dict[rand];
}

$(document).ready(function () {
  var name = $('#userName');
  name.on("keypress", function (e) {
    var self = this;
    var code = (e.keyCode ? e.keyCode : e.which);

    // (code > 33 && code <= 47) || (code > 57 && code <= 63) || code == 17
    // if not an alphabet, letter or number, reject the key, lets irritate them, switch up the key mappings, BURN.
    // this basically kills automated tests :D :D --> Really? Maybe.

    switch (true) {
      // let these fall through, these are ok
      // js only lang to allow this <3
      // will have to figure out arrow keys
      case (code >= 65 && code <= 90):
      case (code >= 48  && code <= 57):
      case (code >= 97  && code <= 122):
      case (code === 32 || code === 13 || code === 9 || code === 27):
        // do nothing and return
        return;
        // break unnecessary, but lets complicate the code
        break;
      case (code > 36 && code < 41):
        // this could be either the arrows or the %(&'
        // if arrows not,morph.  ignore, else -- yoda style
        if ((e.key === '%') || (e.key === '(') || (e.key === '&') || (e.key === '\'')) {

        }
        // fall through
        else
      {
        break;
      }
      case (code > 33 && code <= 47):
      case (code > 57 && code <= 63):
      case (code > 122):
        // these need morphing
        var value = $(self).val();
        value += rn(dict);
        $(self).val(value);
        e.preventDefault();
        break;
    }
  });
});
