package com.gale.craftday.homeworkhelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class HeySmartGuyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String SEARCH_REQUEST = "/search?";
    private static final Pattern SEARCH_URL_PATTERN = Pattern.compile(SEARCH_REQUEST);

    private static final String DISPLAY_GROUPS_PARAMETER = "displayGroups";
    private static final String CONTENT_SETS_PARAMETER = "contentSets";
    private static final String QUERY_PARAMETER = "query";

    private static final String URL_ENCODING = "UTF-8";
    private static final String SEARCH_AND_GET_RESULTS_URL = "http://think.eqa.gghybrid.com/craftday/service/searchAndGetResults";
    private static final int SEARCH_TIMEOUT_MS = 10000;
    private static final String SEARCH_RESULT_CHARSET = "ISO8859_1";

    private static final Logger LOGGER = Logger.getLogger(HeySmartGuyServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String actionRequest = request.getPathInfo();

        if (isSearchRequest(actionRequest)) {
            searchAndGetResults(request, response);
        }
    }

    private boolean isSearchRequest(String actionRequest) {
        Matcher matcher = SEARCH_URL_PATTERN.matcher(actionRequest);
        return matcher.find();
    }

    private void searchAndGetResults(HttpServletRequest request, HttpServletResponse response) {

        String query = request.getParameter(QUERY_PARAMETER);
        String contentSets = request.getParameter(CONTENT_SETS_PARAMETER);
        String displayGroups = request.getParameter(DISPLAY_GROUPS_PARAMETER);

        String searchRequestURL = constructSearchRequestURL(query, contentSets, displayGroups);

        String searchResults = makeSearchRequest(searchRequestURL);

        writeResultsToResponse(searchResults, response);
    }

    private void writeResultsToResponse(String results, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        try {
            PrintWriter out = response.getWriter();
            out.println(results);
            out.close();
        } catch (IOException e) {
            LOGGER.error("Unable to write results to response: " + results, e);
        }
    }

    private String makeSearchRequest(String searchRequestURL) {
        String searchResponse = "[{\"displayGroup\":\"Reference\",\"results\":[{\"resultItem\":{\"itemIdentifier\":\"GALE|CX2661400235\",\"accessLevels\":[{\"id\":\"full-text\",\"mimeType\":\"FULLTEXT\",\"description\":\"full-text\"},{\"id\":\"sios_0001_0002_0_00713-p.pdf|sios_0001_0002_0_00716-p.pdf\",\"mimeType\":\"application/pdf\",\"description\":\"4\"},{\"id\":\"CITATION\",\"mimeType\":\"CITATION\",\"description\":\"CITATION\"}]},\"volumeNumber\":\"2\",\"language\":\"English\",\"docTitle\":\"Innovation\",\"docTitleAnnotation\":\"\",\"newspaperSection\":\"\",\"displayDocTypeList\":[\"Topic overview\"],\"issueNumber\":\"\",\"publicationDate\":\"120080000\",\"startPage\":\"670\",\"endPage\":\"673\",\"pageCount\":\"4\",\"disclaimer\":\"\",\"newspaperEdition\":\"\",\"SICI\":\"\",\"contentSegmentId\":\"\",\"resultSchemaIdentifier\":\"GALE\",\"relevanceWeight\":\"741\",\"keywordInContext\":\"\",\"relatedItems\":[],\"docRoleNameMap\":{\"Illustrator\":{\"names\":[]},\"Editor\":{\"names\":[]},\"Contributor\":{\"names\":[]},\"Author\":{\"names\":[]},\"Translator\":{\"names\":[]}},\"additionaldata\":{\"RF\":\"\",\"MCN_LIBCOL\":\"\",\"MSTYPE\":\"\",\"LX\":\"\",\"SICI\":\"\",\"MCT_MICROFILM\":\"\",\"CIB\":\"\",\"DA_DISP\":\"||2008|||\",\"TP\":\"null\",\"SU\":\"Organization theory|Globalization|Technological innovations|Industrial research|Product development|Economic development|Product management|Organizational change\",\"SUD\":\"\",\"PlotSummary\":\"null\",\"CIT_TYPE\":\"\",\"PARTITION_ID\":\"P1\",\"ABSTRACT\":\"\",\"ENTITY_MAP\":\"\",\"LA_VI\":\"\",\"DTY\":\"zzNumber\",\"SRC_INST\":\"\",\"IB\":\"9781412953900\",\"DPX\":\"CX\",\"MC\":\"1RAA\",\"MD\":\"\",\"LAT_LONG\":\"\",\"IMG_IDS\":\"\",\"BI_NO\":\"\",\"IMAGE_ATTR\":\"\",\"CT\":\"Reference\",\"Snippet\":\"Innovation is now commonly defined as the creation of novelty of economic value. This usually translates into seeing innovation as the creation of new products and services, as the processes of production of these and as\",\"AwardWinner\":\"null\",\"WS_URL\":\"\",\"DRM\":\"\",\"SO\":\"\",\"TI_SORT\":\"\",\"PHY_DESC\":\"\",\"MLA_PRES_SU\":\"\",\"UD\":\"\",\"PO\":\"\",\"WC\":\"2280\",\"PUBLICATION_SUBTITLE\":\"\",\"PDF_IMG_IDS\":\"\",\"FFN\":\"VRL-P1-84\",\"IMPRINT\":\"\",\"LG\":\"English\",\"MF_REEL\":\"\",\"CSID_LIST\":\"\"},\"publicationMetadata\":{\"pubMcode\":\"1RAA\",\"primaryPubMcode\":\"1RAA\",\"tMcode\":\"5KSH\",\"hasEdition\":false,\"pubTitle\":\"International Encyclopedia of Organizational Studies\",\"pubSubTitle\":\"\",\"pubVolumeSubTitle\":\"\",\"pubSeriesTitle\":\"\",\"pubSeriesNumber\":\"\",\"placeOfPublication\":\"Thousand Oaks, CA\",\"pubCopyrightYear\":\"2008\",\"pubCopyrightStatement\":\"COPYRIGHT 2008 SAGE Publications, Inc.\",\"pubPageCount\":\"1639\",\"publisher\":\"Sage Publications, Inc.\",\"pubAddress\":\"ROYAL|Attn: Carol Crandall|2455 Teller Rd.| | |Thousand Oaks| |CA|California|US|United States||\",\"pubVolumeNumber\":\"2\",\"pubVolumeCount\":\"4\",\"pubEdition\":\"\",\"pubRoleNameMap\":{\"Editor\":{\"names\":[{\"prefix\":\"\",\"firstName\":\"Stewart\",\"middleName\":\"R.\",\"lastName\":\"Clegg\",\"suffix\":\"\",\"sobriquet\":\"\",\"rank\":\"1\",\"corporate\":\"\",\"composedName\":\"\",\"affiliation\":\"University of Technology, Sydney, Australia, and University of Aston, Birmingham, UK\",\"roleDescription\":\"\"},{\"prefix\":\"\",\"firstName\":\"James\",\"middleName\":\"R.\",\"lastName\":\"Bailey\",\"suffix\":\"\",\"sobriquet\":\"\",\"rank\":\"2\",\"corporate\":\"\",\"composedName\":\"\",\"affiliation\":\"George Washington University\",\"roleDescription\":\"\"}]},\"Author\":{\"names\":[]}},\"pubWebsiteImprint\":\"\",\"pubLogoImprint\":\"\",\"pubLogoImprintAltText\":\"\",\"pubWebsitePublisher\":\"http://www.sagepub.com\",\"pubLogoPublisher\":\"\",\"pubLogoPublisherAltText\":\"\",\"pubWebsitePublication\":\"\",\"pubLogoPublication\":\"\",\"pubLogoPublicationAltText\":\"\",\"pubCoverPage\":\"sios_0001_0001_0_img0001.jpg|SAGE\",\"pubFrequency\":\"Monograph\",\"pubFormat\":\"Encyclopedia\",\"pubPeerReviewed\":\"N\",\"pubVolumeSubTitleType\":\"Alpha\",\"ISBN\":\"978-1-4129-1515-1\",\"EISBN\":\"978-1-4129-5390-0\",\"ISSN\":\"\",\"imageNameSpace\":\"SAGE\",\"pubAssemblyType\":\"\",\"pubImprint\":\"Sage Publications Inc.\",\"pubSeriesType\":\"\",\"pubSeriesAbbreviation\":\"\",\"pubBookCollectionTitle\":\"\",\"additionaldata\":{\"CopyrightHolderName\":\"Sage Publications, Inc.\",\"ALTERNATE_TITLE\":\"\",\"PMC\":\"5KSH\",\"PUBLICATION_LANGUAGE\":\"English|\",\"JCR_FLAG\":\"\",\"SMC\":\"\",\"TARGET_AUDIENCE\":\"General\"}},\"sourceCitationList\":[],\"rawXml\":\"\",\"recentVersion\":false,\"_new\":false,\"versions\":[]}]},{\"displayGroup\":\"News\",\"results\":[{\"resultItem\":{\"itemIdentifier\":\"GALE|LLOQXK117091698\",\"accessLevels\":[{\"id\":\"full-text\",\"mimeType\":\"FULLTEXT\",\"description\":\"full-text\"},{\"id\":\"NCCOF0012-C00002-N0004215-00030[2121,6202,3144,6283]|NCCOF0012-C00002-N0004215-00030[2123,6279,3154,6987]\",\"mimeType\":\"application/pdf\",\"description\":\"\"},{\"id\":\"CITATION\",\"mimeType\":\"CITATION\",\"description\":\"CITATION\"}]},\"volumeNumber\":\"I\",\"language\":\"English\",\"docTitle\":\"Innovation\",\"docTitleAnnotation\":\"\",\"newspaperSection\":\"Arts and Sports\",\"displayDocTypeList\":[\"Poem\"],\"issueNumber\":\"33\",\"publicationDate\":\"118390817\",\"startPage\":\"131\",\"endPage\":\"\",\"pageCount\":\"1\",\"disclaimer\":\"\",\"newspaperEdition\":\"\",\"SICI\":\"\",\"contentSegmentId\":\"\",\"resultSchemaIdentifier\":\"GALE\",\"relevanceWeight\":\"550\",\"keywordInContext\":\"\",\"relatedItems\":[],\"docRoleNameMap\":{\"Illustrator\":{\"names\":[]},\"Editor\":{\"names\":[]},\"Contributor\":{\"names\":[]},\"Author\":{\"names\":[]},\"Translator\":{\"names\":[]}},\"additionaldata\":{\"RF\":\"Newspaper article record\",\"MCN_LIBCOL\":\"\",\"MSTYPE\":\"9.undefined\",\"LX\":\"\",\"SICI\":\"\",\"MCT_MICROFILM\":\"\",\"CIB\":\"\",\"DA_DISP\":\"August|17|1839|||\",\"TP\":\"null\",\"SU\":\"\",\"SUD\":\"\",\"PlotSummary\":\"null\",\"CIT_TYPE\":\"\",\"PARTITION_ID\":\"P20\",\"ABSTRACT\":\"\",\"ENTITY_MAP\":\"\",\"LA_VI\":\"\",\"DTY\":\"Gale asset\",\"SRC_INST\":\"British Library\",\"IB\":\"\",\"DPX\":\"\",\"MC\":\"5TCH\",\"MD\":\"\",\"LAT_LONG\":\"\",\"IMG_IDS\":\"\",\"BI_NO\":\"\",\"IMAGE_ATTR\":\"\",\"CT\":\"DVI-Newspaper\",\"Snippet\":\"INNOVATION. Wh INNOVATION. Wh When poor Maria first began To mil her youthful charms to mea, Her lovely bosom tbea was made The tempting symbol of her trede ; But since eaoh virtuous blushing deme, With modest care\",\"AwardWinner\":\"null\",\"WS_URL\":\"\",\"DRM\":\"\",\"SO\":\"\",\"TI_SORT\":\"Innovation\",\"PHY_DESC\":\"\",\"MLA_PRES_SU\":\"\",\"UD\":\"\",\"PO\":\"\",\"WC\":\"0\",\"PUBLICATION_SUBTITLE\":\"\",\"PDF_IMG_IDS\":\"NCCOF0012-C00002-N0004215-00030[2121,6202,3144,6283]|NCCOF0012-C00002-N0004215-00030[2123,6279,3154,6987]\",\"FFN\":\"REINDEX_P20_20120316-11-57-21-541_41\",\"IMPRINT\":\"\",\"LG\":\"English\",\"MF_REEL\":\"16\",\"CSID_LIST\":\"\"},\"publicationMetadata\":{\"pubMcode\":\"5TCH\",\"primaryPubMcode\":\"5TCH\",\"tMcode\":\"5629\",\"hasEdition\":false,\"pubTitle\":\"The Odd Fellow\",\"pubSubTitle\":\"\",\"pubVolumeSubTitle\":\"\",\"pubSeriesTitle\":\"\",\"pubSeriesNumber\":\"\",\"placeOfPublication\":\"\",\"pubCopyrightYear\":\"\",\"pubCopyrightStatement\":\"\",\"pubPageCount\":\"\",\"publisher\":\"Primary Source Media\",\"pubAddress\":\"BUS|12 Lunar Drive| | | |Woodbridge| |CT|Connecticut|US|United States||PUB|unknown| | | |London| | | |GB|United Kingdom||\",\"pubVolumeNumber\":\"\",\"pubVolumeCount\":\"1\",\"pubEdition\":\"\",\"pubRoleNameMap\":{\"Editor\":{\"names\":[]},\"Author\":{\"names\":[]}},\"pubWebsiteImprint\":\"\",\"pubLogoImprint\":\"\",\"pubLogoImprintAltText\":\"\",\"pubWebsitePublisher\":\"\",\"pubLogoPublisher\":\"\",\"pubLogoPublisherAltText\":\"\",\"pubWebsitePublication\":\"\",\"pubLogoPublication\":\"\",\"pubLogoPublicationAltText\":\"\",\"pubCoverPage\":\"null|NCCN\",\"pubFrequency\":\"Weekly\",\"pubFormat\":\"Newspaper\",\"pubPeerReviewed\":\"N\",\"pubVolumeSubTitleType\":\"\",\"ISBN\":\"\",\"EISBN\":\"\",\"ISSN\":\"\",\"imageNameSpace\":\"NCCN\",\"pubAssemblyType\":\"\",\"pubImprint\":\"\",\"pubSeriesType\":\"\",\"pubSeriesAbbreviation\":\"\",\"pubBookCollectionTitle\":\"\",\"additionaldata\":{\"CopyrightHolderName\":\"Primary Source Media\",\"ALTERNATE_TITLE\":\"\",\"PMC\":\"4ZNB\",\"PUBLICATION_LANGUAGE\":\"English|\",\"JCR_FLAG\":\"\",\"SMC\":\"\",\"TARGET_AUDIENCE\":\"Academic\"}},\"sourceCitationList\":[],\"rawXml\":\"\",\"recentVersion\":false,\"_new\":false,\"versions\":[]}]}]";

        return searchResponse;
    }

    private String constructSearchRequestURL(String query, String contentSets, String displayGroups) {
        return String.format("%s/%s/displayGroups=%s&startPos=1&pageSize=5", SEARCH_AND_GET_RESULTS_URL,
                encodeParameter(query), encodeParameter(displayGroups));
    }

    private String encodeParameter(String parameter) {
        String encodedParameter = "";

        if (parameter != null) {
            try {
                encodedParameter = URLEncoder.encode(parameter, URL_ENCODING);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("Unable to encode the following parameter; skipping parameter: " + parameter, e);
            }
        }
        return encodedParameter;
    }
}
