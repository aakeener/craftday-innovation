<!DOCTYPE html> 
<html>
   <head>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <title>Mobile - Go ahead.  Ask me.</title>
      <link rel="stylesheet" href="http://code.jquery.com/mobile/latest/jquery.mobile.css" />
      <link rel="stylesheet" href="mobile.css" />
      <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
      <script src="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.js"></script>
      <script type="text/javascript" src="scripts/searchAndGetResults.js"></script>
   </head>
   <body>
      <div data-role="page" class="type-interior">
         <div data-role="header" data-theme="b">
            <h1>Homework ?</h1>
         </div>
         <div data-role="content" data-theme="c">
            <div class="content-primary">
               <form name="search" action="">
                  <div data-role="fieldcontain">
                     <label for="title">What topic do you need more information on?</label>
                     <input type="text" name="query" id="query" value="" data-mini="true" />
                  </div>
                  <div data-role="fieldcontain">
                     <fieldset data-role="controlgroup">
                        <label for="contentSets" class="select">Which product would you like to search in?</label>
                        <select name="contentSets" id="contentSets" data-mini="true">
                           <option value="ZXAY-MOD1|ZXBA-MOD1|ZXAB-VRL|ZXBC-VRL|OV-Misc">Opposing Viewpoints in Context</option>
                           <option value="ZXBK-MOD1|ZXBM-MOD1|ZXAP-VRL|ZXBO-VRL|Student-Misc">Student in Context</option>
                           <option value="ZXBE-MOD1|ZXBG-MOD1|ZXAR-VRL|ZXBI-VRL|Science-Misc">Science in Context</option>
                        </select>
                     </fieldset>
                  </div>
                  <div data-role="fieldcontain">
                     <fieldset data-role="controlgroup">
                        <label for="displayGroups" class="select">What type of documents are you in interested in?</label>
                        <select name="displayGroups" id="contentSets" data-mini="true">
                           <option value="K12-Reference">Reference</option>
                           <option value="K12-News">News</option>
                           <option value="None">Everything</option>
                        </select>
                     </fieldset>
                  </div>
                  <button type="submit" id="submitSearch" name="submitSearch" data-theme="b" data-mini="true">Submit</button>
               </form>
               <div class="ui-body ui-body-d ui-corner-all">
                  <section id="error">
                  </section>
                  <section id="searchResultsHeader">
                  </section>
                  <section id="searchResultsList">
                  </section>
               </div>
            </div>
         </div>
      </div>
   </body>
</html>