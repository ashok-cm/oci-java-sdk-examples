package com.oracle.oci.sdk.examples.storage;

import java.util.List;

import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.model.BucketSummary;
import com.oracle.bmc.objectstorage.requests.ListBucketsRequest;
import com.oracle.bmc.objectstorage.responses.ListBucketsResponse;
import com.oracle.oci.sdk.OCIConfig;

/*
 * Sample code to get list of buckets based on region, compartment, name space.
 * You may still add more select criteria in ListBucketsRequest like compartmentId, name space.
 * 
 * Sample Output (Masked):
 * [Bucket] Name: tagtestbucket3, Namespace: fa...cy
 * [Bucket] Name: testbucket1, Namespace: fa...cy
 * [Bucket] Name: testbucket2, Namespace: fa...cy
 */

public class ListBuckets {
    public static void main(String args[]) throws Exception {
    	
    	AuthenticationDetailsProvider adp = OCIConfig.getAuthenticationDetailsProvider();
    	Region region = Region.US_ASHBURN_1;
    	String compartmentId = "ocid1.tenancy.oc1..aaaaaaaasu7rvefmsyk5kqczfmdqi5clpddejfjk2attdqnk6sbk72wajq5q";
    	String namespaceName = "fahdabidiroottenancy";
    	
    	ListBucketsRequest request = ListBucketsRequest.builder()
    			.compartmentId(compartmentId)
    			.namespaceName(namespaceName)
    			.build();
        
    	List<BucketSummary> buckets = getBuckets(adp, region, request);
    	
    	buckets.stream().forEach((BucketSummary bucket) -> {
    		System.out.println("[Bucket] Name: " + bucket.getName() + ", Namespace: " + bucket.getNamespace());
    	});
    }
    
    public static List<BucketSummary> getBuckets(AuthenticationDetailsProvider adp, Region region, ListBucketsRequest request) throws Exception {
    	ObjectStorageClient client = ObjectStorageClient.builder().build(adp);
        client.setRegion(region);
        ListBucketsResponse response = client.listBuckets(request);
        return response.getItems();
    }
}
