<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8" />
      <title>Go ahead.  Ask me.</title>
      <meta name="description" content="" />
      <meta name="author" content="" />
      <!-- HTML5 support for IE6-8 support of HTML elements -->
      <!--[if lt IE 9]>
         <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
      <![endif]-->
      <link href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css" rel="stylesheet" />
   </head>
   <body>
      <!-- Navigation bar -->
      <div class="topbar">
         <div class="topbar-inner">
            <div class="container">
               <a class="brand" href="http://cengage.com/">Look smart.</a>
               <ul class="nav">
                  <li><a href="#">Go head.  Ask us.</a> </li>
               </ul>
            </div>
         </div>
      </div>
      <!-- Header -->
      <header class="info" id="overview">
         <div class="inner">
            <div class="container">
               <h1>Homework Helper from Gale|Cengage</h1>
            </div>
         </div>
      </header>
      <div class="container" />
      <section id="searchForm" />
      <div class="page-header">
         <h1>What's your homework question?</h1>
      </div>
      <div class="row">
         <div class="span5">
            <h4>Don't let homework get you down.</h4>
            <p>Get help from an authoritative expert, Gale|Cengage.</p>
         </div>
         <div class="span7">
            <form name="search" action="" class="form-stacked">
               <fieldset>
                  <div class="clearfix">
                     <label for="query">What topic do you need more information on?</label>
                     <div class="input">
                        <input class="xlarge" id="query" name="query" type="text" />
                     </div>
                  </div>
                  </br>
                  <div class="clearfix">
                     <label for="contentSet">Which product would you like to search in?</label>
                     <div class="input">
                        <select class="select xlarge" name="contentSets" id="contentSets">
                           <option value="ZXAY-MOD1|ZXBA-MOD1|ZXAB-VRL|ZXBC-VRL|OV-Misc">Opposing Viewpoints in Context</option>
                           <option value="ZXBK-MOD1|ZXBM-MOD1|ZXAP-VRL|ZXBO-VRL|Student-Misc">Student in Context</option>
                           <option value="ZXBE-MOD1|ZXBG-MOD1|ZXAR-VRL|ZXBI-VRL|Science-Misc">Science in Context</option>
                        </select>
                     </div>
                  </div>                  
                  </br>
                  <div class="clearfix">
                     <label for="displayGroups">What type of documents are you in interested in?</label>
                     <div class="input">
                        <select class="select xlarge" name="displayGroups" id="displayGroups">
                           <option value="K12-Reference">Reference</option>
                           <option value="K12-News">News</option>
                           <option value="None">Everything</option>
                        </select>
                     </div>
                  </div>
               </fieldset>
               <div class="actions">
                  <button id="submitSearch" type="submit" class="btn primary">Search</button>
               </div>
            </form>
         </div>
      </div>
      </section>
      <section id="error">
      </section>
      <section id="searchResultsHeader">
      </section>
      <section id="searchResultsList">
      </section>
      <footer class="footer">
         <div class="container">
            <p>
               Designed and built with learning and innovation in mind.
            </p>
         </div>
      </footer>

      <!-- Javascript -->
      <script type="text/javascript"  src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
      <script type="text/javascript"  src="scripts/searchAndGetResults.js"></script>      

   </body>
</html>