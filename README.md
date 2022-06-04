# DCE-Capstone-GDC-T4L2
DCE Capstone GDC Project from Team 4 for Level 2

## Overview
The following repo contains Code form Banking Mortgage Process.

### Customer Problem
The current process require submitting different customer requests in inside portal by Customer Relationship Specialists (based on phone conversations with different customers). The Inside Portal provides an ability to export requests for a complete day in the form of csv file. This csv file is then used as input to create customer specific Documents with relevant Terms and priviledge detailed Templates (current process involving manual steps). Once generated,  these documents require customer signature to confirm the acceptance of Terms and discussed plan. 

## System Requirement
- Github account to be a collaborator
- Ecllipse or Intellij to do development of extension of current code branch.
- Java 8 or above to execute the application
- Any Web Browser to work with Application UI.

## Guidelines - Getting Started
### Obtaining Credentials and Downloading Sample Files
Please refer to article: https://experienceleague.adobe.com/docs/document-services/tutorials/pdfservices/gettingstartedjava.html?lang=en

### The code is still to be added as part of the repository.

Once code is added, the next step will be as:
1. Clone this repository
2. Go to release directory.


## Running of Application
### Required inputs
- The csv file with details for each customer

### Sample for csv file will be provided shortly.
### Details of Execution will be published once the code is added.
Current Process

 ![image](https://user-images.githubusercontent.com/51231889/172003642-e68d8a69-01e5-4263-b6ef-215d4e1fac98.png)


Final Process

![image](https://user-images.githubusercontent.com/51231889/172003669-c44a8342-cf69-4cae-a630-32beba8c8f4c.png)

 
Requirements
For building and running the application you need:
•	JDK 1.8
•	Maven 3

Installation
To install the Document Service API, upgrade the pdfservices-api-credentials.json with right client and account credentials, generated from https://developer.adobe.com/
To install the API client library to your local repository:
At first generate the JAR either by executing:
mvn package
Then manually install the following JARs:
•	swagger-java-client-1.0.0.jar
•	okhttp-2.7.5.jar
•	gson-2.8.1.jar
•	gson-fire-1.8.0.jar
or
gradle build
Then manually install the following JARs:
•	swagger-java-client-1.0.0.jar
•	okhttp-2.7.5.jar
•	gson-2.8.1.jar
•	gson-fire-1.8.0.jar
Getting Started
The end user needs to follow the below mentioned steps to get started with:
1.	Once the correct format ed CSV is uploaded, method runDocGenProcess get called followed by relevant unique ids, as required. It basically loads the JSON format as per the tags, calculated fields or conditions into the sample document and then return the success response else failure. 

2.	public String runDocGenProcess(String content, String custID) throws IOException {
    try {

        // Initial setup, create credentials instance.
        Credentials credentials = Credentials.serviceAccountCredentialsBuilder()
                .fromFile("pdfservices-api-credentials.json")
                .build();

        // Setup input data for the document merge process
        JSONObject jsonDataForMerge = new JSONObject(content);

        // Create an ExecutionContext using credentials.
        ExecutionContext executionContext = ExecutionContext.create(credentials);

        //Create a new DocumentMergeOptions instance
        DocumentMergeOptions documentMergeOptions = new DocumentMergeOptions(jsonDataForMerge, OutputFormat.PDF);

        // Create a new DocumentMergeOperation instance with the DocumentMergeOptions instance
        DocumentMergeOperation documentMergeOperation = DocumentMergeOperation.createNew(documentMergeOptions);

        // Set the operation input document template from a source file.
        FileRef documentTemplate = FileRef.createFromLocalFile("filepath");
        documentMergeOperation.setInput(com.adobe.pdfservices.operation.io.FileRef.createFromLocalFile("provide filepath to store into local",
                DocumentMergeOperation.SupportedSourceFormat.DOCX.getMediaType()));
            // Execute the operation
            FileRef result = documentMergeOperation.execute(executionContext);

            // Save the result to the specified location.
            result.saveAs("filePath+fileName"+custID+".pdf");


    }catch (ServiceApiException | IOException | SdkException | ServiceUsageException ex) {
        LOGGER.error("Exception encountered while DocGen executing operation", ex);
    }
    return "success";
}
3.	Next, in order to hit the Adobe Acrobat Sign API, user need to follow the below steps in order to get started with it:
3.1	In order to use the SDK, you need to have an account with Adobe Sign. Please register for a developer account here.
3.2	 Sign in to create an application on the Adobe Sign web portal and obtain it's application id and application secret.
3.3	 Generate the OAuth access token by using the above application id and the application secret. The access token will need to be generated by using the OAuth APIs.
3.4	 Use the generated OAuth access token for trying out the sample API code      given below.
3.5	In order to use the SDK, you need to have an account with Adobe Sign. Please register for a developer account here.
3.6	Sign in to create an application on the Adobe Sign web portal and obtain it's application id and application secret.
3.7	Generate the OAuth access token by using the above application id and the application secret. The access token will need to be generated by using the OAuth APIs.
3.8	Use the generated OAuth access token for trying out the sample API code given below.
public String sendForSignature(String customerId, String email){
    System.out.println("*************** Entered into sending agreement ***********");
    System.out.println(customerId);
    String id = null;
    try {

        //ApiClient apiClient = new ApiClient();

        ApiClient apiClient= Configuration.getDefaultApiClient();

        System.out.println("*********************************");

                    //Default baseUrl to make GET /baseUris API call.
        String baseUrl = "https://api.echosign.com/";
        String endpointUrl = "/api/rest/v6";
        apiClient.setBasePath(baseUrl + endpointUrl);

        //TODO : Provide an OAuth Access Token as "Bearer : access token" in authorization
        String authorization = "Bearer Access Token";

        //Get the baseUris for the user and set it in apiClient.
        BaseUrisApi baseUrisApi = new BaseUrisApi(apiClient);
        BaseUriInfo baseUriInfo = baseUrisApi.getBaseUris(authorization);
        apiClient.setBasePath(baseUriInfo.getApiAccessPoint() + endpointUrl);

        //TODO : Provide path and name of file to be uploaded as transient document
        String filePath = "filepath"+customerId+".pdf";
        String fileName = customerId;
        File file = new File(filePath);
        String xApiUser = null;
        String xOnBehalfOfUser = null;
        String mimeType = "application/pdf";

        //Get the id of the transient document.
        TransientDocumentsApi transientDocumentsApi = new TransientDocumentsApi(apiClient);
        TransientDocumentResponse response = transientDocumentsApi.createTransientDocument(authorization, file, xApiUser, xOnBehalfOfUser, fileName, mimeType);
        String transientDocumentId = response.getTransientDocumentId();
        System.out.println("************* Trans Doc ID : " +transientDocumentId);

        //prepare request body for agreement creation.
        AgreementCreationInfo agreementInfo=new AgreementCreationInfo();
        agreementInfo.setName("Name");
        agreementInfo.setSignatureType(AgreementCreationInfo.SignatureTypeEnum.ESIGN);
        agreementInfo.setState(AgreementCreationInfo.StateEnum.IN_PROCESS);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setTransientDocumentId(transientDocumentId);
        agreementInfo.addFileInfosItem(fileInfo);

        ParticipantSetInfo participantSetInfo = new ParticipantSetInfo();
        ParticipantSetMemberInfo participantSetMemberInfo = new ParticipantSetMemberInfo();

        //TODO : Provide email of recipient to whom agreement will be sent
        participantSetMemberInfo.setEmail(email);
        participantSetInfo.addMemberInfosItem(participantSetMemberInfo);
        participantSetInfo.setOrder(1);
        participantSetInfo.setRole(ParticipantSetInfo.RoleEnum.SIGNER);
        agreementInfo.addParticipantSetsInfoItem(participantSetInfo);

        //Create agreement using the transient document.
        AgreementsApi agreementsApi = new AgreementsApi(apiClient);
        AgreementCreationResponse agreementCreationResponse = agreementsApi.createAgreement(authorization, agreementInfo, xApiUser, xOnBehalfOfUser);
        id = agreementCreationResponse.getId();
        System.out.println("********************* Agreement ID " +id); 
}
    catch (Exception e) {
        System.err.println(e.toString());
    }
    return id;
}

Recommendation
It's recommended to create an instance of ApiClient per thread and per user (with baseUris fetched for user) in a multithreaded environment to avoid any potential issues.

Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the adobe-sign-main\src\main\java\com\hr\docsigning\ DocsigningApplication class from your IDE.


Steps to run
1.	Build the project using mvn clean install
2.	Run using mvn spring-boot:run
3.	The web application is accessible via localhost:8080



## More Info
More information on different APIs used as part of this project is available at:
- Adobe Acrobat Sign APIs: https://na1.echosign.com/public/docs/restapi/v6#!/
- DCE Doc Gen APIs: https://experienceleague.adobe.com/docs/document-services/tutorials/docgen/overview-docgen.html?lang=en
- DCE Extract APIs: https://experienceleague.adobe.com/docs/document-services/tutorials/pdfextract/overview-extract.html?lang=en

## Disclaimers
- This is currently in development and will require couple of days to publish a stable working version.
- It is possible that currently this repo is not actively maintained.

## License
The code is this repository is available for all to use and get benefits from. This may soon be published as Open code to the community.

## Contributing
Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are greatly appreciated.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement". Don't forget to give the project a star! Thanks again!

Fork the Project
1. Create your Feature Branch (git checkout -b feature/AmazingFeature)
2. Commit your Changes (git commit -m 'Add some AmazingFeature')
3. Push to the Branch (git push origin feature/AmazingFeature)
4. Open a Pull Request

## Acknowledgement
Following Resources were very useful:
- Doc Gen https://experienceleague.adobe.com/docs/document-services/tutorials/docgen/docgentemplates/taggerconditional.html?lang=en

## Support
In case of any questions, please contact us at gdutt@adobe.com
