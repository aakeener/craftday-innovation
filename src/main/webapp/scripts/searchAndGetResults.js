var INNOVATION = {}; (function($) {

    INNOVATION.askHomeworkHelper = {
        init: init
    };

    function init() {
        bindClearEvents();
        $('#submitSearch').click(searchAndGetResults);
    }

    function bindClearEvents() {
        $('#query').focus(clearPreviousResults);
        $('#contentSets').focus(clearPreviousResults);
        $('#displayGroups').focus(clearPreviousResults);

        function clearPreviousResults() {
            $('#error').empty();
            $('#searchResultsHeader').empty();
            $('#searchResultsList').empty();
            return false;
        }
    }

    function searchAndGetResults() {

        var userSearchQuery = $('#query').val();
        var userContentSets = $('#contentSets').val();
        var userDisplayGroups = $('#displayGroups').val();

        if (userSearchQuery === '') {
            $('#error').html('<div class="row"><div class="span5">&nbsp;</div><div class="span7"><h3>Paul Tunney will kill me if I let you submit that search.<br/>Please search on another topic.</h3></div></div>');
        } else {

            $.getJSON("/HeySmartGuy/search?query=" + userSearchQuery + "&contentSets=" + userContentSets + "&displayGroups=" + userDisplayGroups, function(json) {
                $('#searchResultsHeader').html('<div class="row"><div class="span12"><h2>Search Results</h2></div></div>');
                $('#searchResultsList').append('<div class="row"><div class="span12"><table id="searchResultsTable" class="bordered-table zebra-striped"><thead><tr><th>Title</th><th>Publication</th><th>Description</th></tr></thead><tbody></tbody></table></div></div>');
                if (json !== '') {
                    $.each(json[0].results, function(i, result) {
                        $('#searchResultsTable tbody').append('<tr><td>' + result.docTitle + '</td><td>' + result.publicationMetadata.pubTitle + '</td><td>' + result.additionaldata.Snippet + '...</td></tr>');
                    });
                } else {
                    $('#searchResultsList').html('<div class="row"><h3>Tell them your dog ate your homework.<h3></div>');
                }
            });

        }
        return false;
    }

})(jQuery);

$(document).ready(function() {
    INNOVATION.askHomeworkHelper.init();
});