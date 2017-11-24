/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2017 Adobe Systems Incorporated
 ~
 ~ Licensed under the Apache License, Version 2.0 (the 'License');
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an 'AS IS' BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
/*global Coral,jQuery*/
(function ($) {
    'use strict';

    var DIALOG_CONTENT_SELECTOR = '.cmp-navigation__editor',
        COLLECT_ALL_PAGES_SELECTOR = DIALOG_CONTENT_SELECTOR + ' coral-checkbox[name="./collectAllPages"]',
        MAX_DEPTH_SELECTOR = DIALOG_CONTENT_SELECTOR + ' coral-numberinput[name="./maxDepth"]';

    $(window).adaptTo('foundation-registry').register('foundation.adapters', {
        type: 'foundation-toggleable',
        selector: MAX_DEPTH_SELECTOR,
        adapter: function(el) {
            var toggleable = $(el);
            return {
                isOpen: function() {
                    return !toggleable.adaptTo('foundation-field').isDisabled();
                },
                show: function() {
                    toggleable.adaptTo('foundation-field').setDisabled(false);
                    toggleable.parent().show();
                },
                hide: function() {
                    toggleable.adaptTo('foundation-field').setDisabled(true);
                    toggleable.parent().hide();
                }
            };
        }
    });

    function toggleMaxDepth(collectAllPages) {
        if (collectAllPages) {
            Coral.commons.ready(document.querySelector(MAX_DEPTH_SELECTOR), function (maxDepth) {
                if (collectAllPages.checked) {
                    $(maxDepth).adaptTo('foundation-toggleable').hide();
                } else {
                    $(maxDepth).adaptTo('foundation-toggleable').show();
                }
            });
        }
    }

    $(document).on('coral-component:attached', COLLECT_ALL_PAGES_SELECTOR, function () {
        toggleMaxDepth(this);
    });

    $(document).on('change', COLLECT_ALL_PAGES_SELECTOR, function () {
        toggleMaxDepth(this);
    });

})(jQuery);