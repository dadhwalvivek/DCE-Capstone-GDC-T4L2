package com.hr.docsigning;

//import com.adobe.platform.operation.ExecutionContext;
//import com.adobe.platform.operation.auth.Credentials;
import com.adobe.platform.operation.exception.SdkException;
import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.platform.operation.exception.ServiceUsageException;
//import com.adobe.platform.operation.io.FileRef;
        import io.swagger.client.model.Configuration;
import io.swagger.client.model.baseUris.BaseUriInfo;
import io.swagger.client.model.transientDocuments.TransientDocumentResponse;
        import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
        import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.adobe.pdfservices.operation.pdfops.DocumentMergeOperation;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.DocumentMergeOptions;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.OutputFormat;
import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.auth.Credentials;

import io.swagger.client.api.AgreementsApi;
import io.swagger.client.api.BaseUrisApi;
import io.swagger.client.api.TransientDocumentsApi;
import io.swagger.client.model.ApiClient;
        import io.swagger.client.model.agreements.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
        import java.util.ArrayList;
        import java.util.Iterator;
import java.util.List;

import com.hr.docsigning.model.*;


@Controller
public class PersonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
    private String contractFilePath = "output/contract.pdf";

    @GetMapping("/")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        //CreateContract(personForm);

        return "contract-actions";
    }

   // @PostMapping("/fileupload")
    @RequestMapping(value="/fileupload", method = RequestMethod.POST)
    private String readDataFromCSV(MultipartFile file, HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException {
       // if(true) {
//            String html_response = "<html><head>Our processing response</head>" +
//                    "<body> Welcome to our page. Your request is processed </body></html>";
//            String name = req.getParameter("empName");
//            model.addAttribute("empName", name);
//            return "contract-actions";
//            System.out.println("Gagan .. Entered inside the method ..... ResponseEntity");
//            model.addAttribute("Title", "This is my specific title");
//            model.addAttribute("Subtitle", "This is the other one");
//
//            return "contract-actions";


      //  }
      //  displayData(req,resp, "1234","prsah@adobe.com","XYZ1234567890");
      //  else {
            String[] arr;
            String line;
            List<FileModel> cellDataList = new ArrayList();
            try {
                System.out.println("************ Reading data from the file **************** ");
                StringBuilder sb = new StringBuilder();
                InputStream is = file.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                int count = 0;
                while ((line = br.readLine()) != null) {
//                if(count < 1){
//                    count++;
//                    continue;
//                }
                    System.out.println("\t\tThe read line is: ... " + line);
                    System.out.println(line.length());
                    arr = line.split(",");
                    System.out.println(arr[4]);

                    FileModel fileModel = new FileModel(arr[0], arr[1], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10], arr[11], arr[12], arr[13], arr[14],
                            arr[15], arr[16], arr[17], arr[18], arr[19], arr[20], arr[21], arr[22], arr[23], arr[24], arr[25], arr[2]);
                    cellDataList.add(fileModel);


                    //fileModel.getJSONResponse();
                }
                System.out.println("****************************");
                Iterator<FileModel> iteratorCell = cellDataList.iterator();
                while (iteratorCell.hasNext()) {
                    FileModel fm = iteratorCell.next();
                    System.out.println("\n\t ******** JSON Response *******" + fm.getJSONResponse());
                    runDocGenProcess(fm.getJSONResponse(), fm.getCustomerId());
                    String custID=fm.getCustomerId();
                    String emailid=fm.getEmail();
                    String agreementID = sendForSignature(custID, emailid);
                    fm.setAgreementID(agreementID);

                    model.addAttribute("CustomerID", custID);
                    model.addAttribute("TransactionID", agreementID);
                    model.addAttribute("EmailID",emailid);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
                 return "contract-actions";
        }



   // }


    public int runDocGenProcess(String content, String custID) throws IOException {
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
            FileRef documentTemplate = FileRef.createFromLocalFile("src/main/resources/SettlementTemplate.docx");
            documentMergeOperation.setInput(com.adobe.pdfservices.operation.io.FileRef.createFromLocalFile("src/main/resources/SettlementTemplate.docx",
                    DocumentMergeOperation.SupportedSourceFormat.DOCX.getMediaType()));
                // Execute the operation
                FileRef result = documentMergeOperation.execute(executionContext);

                // Save the result to the specified location.
                result.saveAs("C:\\Users\\prsah\\OneDrive - Adobe\\DCE Training\\Capstone-Project-Adobe-DCE\\SettlementLetter-"+custID+".pdf");


        }catch (ServiceApiException | IOException | SdkException | ServiceUsageException ex) {
            LOGGER.error("Exception encountered while DocGen executing operation", ex);
        }
        return 1;
    }
    public String sendForSignature(String customerId, String email){
        System.out.println("*************** Entered into sending agreement ***********");
        System.out.println(customerId);
        String id = null;
        try {

            //ApiClient apiClient = new ApiClient();

            ApiClient apiClient= Configuration.getDefaultApiClient();

            System.out.println("*********************************");

                        //Default baseUrl to make GET /baseUris API call.
            String baseUrl = "https://api.na2.echosign.com/";
            String endpointUrl = "/api/rest/v6";
            apiClient.setBasePath(baseUrl + endpointUrl);

            //TODO : Provide an OAuth Access Token as "Bearer : access token" in authorization
            String authorization = "Bearer 3AAABLblqZhDw4arRj_iGFiVp5TvYaVr778D_Ce4plRjQIP4HpDUG-ulpAYFB8IdDpiXGbAQ6iV6kuPxn5hVuaEHgvLj4L3Jh";

            //Get the baseUris for the user and set it in apiClient.
            BaseUrisApi baseUrisApi = new BaseUrisApi(apiClient);
            BaseUriInfo baseUriInfo = baseUrisApi.getBaseUris(authorization);
            apiClient.setBasePath(baseUriInfo.getApiAccessPoint() + endpointUrl);

            //TODO : Provide path and name of file to be uploaded as transient document
            String filePath = "C:\\Users\\prsah\\OneDrive - Adobe\\DCE Training\\Capstone-Project-Adobe-DCE\\SettlementLetter-"+customerId+".pdf";
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
            agreementInfo.setName("DCE-Testing");
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


           // Get agreement info using the agreement id.
//            String ifNoneMatch = null;
//            agreementInfo = agreementsApi.getAgreementInfo(authorization, id, xApiUser, xOnBehalfOfUser, ifNoneMatch);
//            System.out.println("Agreement ID = " + agreementInfo.getId());
//            System.out.println("Agreement Name = " + agreementInfo.getName());
//            System.out.println("Agreement Status = " + agreementInfo.getStatus());


        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
        return id;
    }
}



