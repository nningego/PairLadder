/*
    Copyright 2011 Michael Rylander

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

    Description of Purpose: js for pair ladder
*/

toggle = 0;

$(document).ready(function() {
    var data = read_cookie(pair_cookie_name);


    if (data == null) {
        show_popup_modal()
        return
    }

    construct_pair_ladder();

    $("#top_row_names .remove_developer").click(function() {
        remove_a_dev(this);
        return false;
    });

    toggle_from_default_view()
    init_hover_animation();
});


