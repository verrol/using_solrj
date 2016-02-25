package org.lorrev;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args)  throws MalformedURLException, SolrServerException, IOException {
        Logger logger = LoggerFactory.getLogger(Main.class);

        logger.info("Staring up SolrJ application...");


        String zkHostString = "10.17.80.254:2181,10.17.80.29:2181,10.17.80.34:2181/solr";

        CloudSolrClient server = new CloudSolrClient(zkHostString);
        server.setParser(new XMLResponseParser());
        SolrQuery parameters = new SolrQuery();
        parameters.set("q", "*:*");
        parameters.set("qt", "/select");
        parameters.set("collection", "collection1");
        QueryResponse response = server.query(parameters);
        SolrDocumentList list = response.getResults();
        logger.info("Query result size: {}", list.size());
    }
}