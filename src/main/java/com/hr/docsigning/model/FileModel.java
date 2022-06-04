package com.hr.docsigning.model;

public class FileModel {
    private String Title;
    private String Name;
    private String Street;
    private String City;
    private String State;
    private String Zip;
    private String CustomerId;
    private String CCNo;
    private String Segment;
    private String Limit;
    private String Outstanding;
    private String Settlement;
    private String DueDate;
    private String S1No;
    private String S1Mode;
    private String S1Bank;
    private String S1Branch;
    private String S1Date;
    private String S1Amount;
    private String S2No;
    private String S2Mode;
    private String S2Bank;
    private String S2Branch;
    private String S2Date;
    private String S2Amount;
    private String email;

    private String agreementID;
    String JSONRes;

//	private String Title;
//	private String Name;
//	private long Phone;
//	private String Street;
//	private String City;
//	private String State;
//	private long Zip;
//	private String CustomerId;
//	private Double CCNo;
//	private String Segment;
//	private long Limit;
//	private long Outstanding;
//	private long Settlement;
//	private String DueDate;
//	private int S1No;
//	private String S1Mode;
//	private String S1Bank;
//	private String S1Branch;
//	private String S1Date;
//	private long S1Amount;
//	private int S2No;
//	private String S2Mode;
//	private String S2Bank;
//	private String S2Branch;
//	private String S2Date;
//	private long S2Amount;

    /*	public FileModel (String Title,String Name,long Phone,
                 String Street,String City,String State,long Zip,String CustomerId,Double CCNo,
                 String Segment,long Limit,long Outstanding,long Settlement,String DueDate,
                 int S1No,String S1Mode,String S1Bank,String S1Branch,String S1Date,
                 long S1Amount,int S2No,String S2Mode,String S2Bank,String S2Branch,
                 String S2Date,long S2Amount ) {
            this.Title = Title;

        }
    */
    public FileModel (String Title,String Name,String Street,String City,String State,
                      String Zip,String CustomerId,String CCNo,String Segment,String Limit,String Outstanding,
                      String Settlement,String DueDate,String S1No,String S1Mode,String S1Bank,String S1Branch,
                      String S1Date,String S1Amount,String S2No,String S2Mode,String S2Bank,String S2Branch,
                      String S2Date,String S2Amount,String email)
    {
        this.Title=Title;
        this.Name=Name;
        this.Street=Street;
        this.City=City;
        this.State=State;
        this.Zip=Zip;
        this.CustomerId=CustomerId;
        this.CCNo=CCNo;
        this.Segment=Segment;
        this.Limit=Limit;
        this.Outstanding=Outstanding;
        this.Settlement=Settlement;
        this.DueDate=DueDate;
        this.S1No=S1No;
        this.S1Mode=S1Mode;
        this.S1Bank=S1Bank;
        this.S1Branch=S1Branch;
        this.S1Date=S1Date;
        this.S1Amount=S1Amount;
        this.S2No=S2No;
        this.S2Mode=S2Mode;
        this.S2Bank=S2Bank;
        this.S2Branch=S2Branch;
        this.S2Date=S2Date;
        this.S2Amount=S2Amount;
        this.email=email;
    }

    public FileModel() {

    }

    //Getter and Setter for Name
    //Getter for Name
    public String getName() {
        return Name;
    }
    //Setter for Name
    public void setName(String name) {
        Name = name;
    }


    //Getter and Setter for Street
    //Getter for Street
    public String getStreet() {
        return Street;
    }
    //Setter for Street
    public void setStreet(String street) {
        Street = street;
    }

    //Getter and Setter for City
    //Getter for City
    public String getCity() {
        return City;
    }
    //Setter for City
    public void setCity(String city) {
        City = city;
    }

    //Getter and Setter for State
    //Getter for State
    public String getState() {
        return State;
    }
    //Setter for State
    public void setState(String state) {
        State = state;
    }

    //Getter and Setter for Zip
    //Getter for Zip
    public String getZip() {
        return Zip;
    }
    //Setter for Zip
    public void setZip(String zip) {
        Zip = zip;
    }

    //Getter and Setter for Customer ID
    //Getter for Customer Id
    public String getCustomerId() {
        return CustomerId;
    }
    //Setter for Customer ID
    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    //Getter and Setter for CC Number
    //Getter for CC Number
    public String getCCNo() {
        return CCNo;
    }
    //Setter for CC Number
    public void setCCNo(String cCNo) {
        CCNo = cCNo;
    }

    //Getter and Setter for Segment
    //Getter for Segment
    public String getSegment() {
        return Segment;
    }
    //Setter for Segment
    public void setSegment(String segment) {
        Segment = segment;
    }

    //Getter and Setter for Limit
    //Getter for LImit
    public String getLimit() {
        return Limit;
    }
    //Setter for Limit
    public void setLimit(String limit) {
        Limit = limit;
    }

    //Getter and Setter for Outstanding Amount
    //Getter for Outstanding amount
    public String getOutstanding() {
        return Outstanding;
    }
    //Setter for Outstanding amount
    public void setOutstanding(String outstanding) {
        Outstanding = outstanding;
    }

    //Getter and Setter for Settlement amount
    //Getter for Settlement amount
    public String getSettlement() {
        return Settlement;
    }
    //Setter for Settlement amount
    public void setSettlement(String settlement) {
        Settlement = settlement;
    }

    //Getter and Setter for Due Date
    //Getter for Due Date
    public String getDueDate() {
        return DueDate;
    }
    //Setter for Due Date
    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    //Getter and Setter for S1No
    //Getter for S1 No
    public String getS1No() {
        return S1No;
    }
    //Setter for S1 No.
    public void setS1No(String s1No) {
        S1No = s1No;
    }

    //Getter and Setter for S1 Mode
    //Getter for S1 Mode
    public String getS1Mode() {
        return S1Mode;
    }
    //Setter for S1 Mode
    public void setS1Mode(String s1Mode) {
        S1Mode = s1Mode;
    }

    //Getter and Setter for Bank S1
    //Getter for Bank S1
    public String getS1Bank() {
        return S1Bank;
    }
    //Setter for S1 Bank
    public void setS1Bank(String s1Bank) {
        S1Bank = s1Bank;
    }

    //Getter and Setter for S1 Branch
    //Getter for Branch S1
    public String getS1Branch() {
        return S1Branch;
    }
    //Setter for Branch
    public void setS1Branch(String s1Branch) {
        S1Branch = s1Branch;
    }


    //Getter and Setter for S1 Due
    //Getter for S1 Due
    public String getS1Date() {
        return S1Date;
    }
    //Setter for S1 Due
    public void setS1Date(String s1Date) {
        S1Date = s1Date;
    }

    public String getS1Amount() {
        return S1Amount;
    }

    public void setS1Amount(String s1Amount) {
        S1Amount = s1Amount;
    }

    public String getS2No() {
        return S2No;
    }

    public void setS2No(String s2No) {
        S2No = s2No;
    }

    public String getS2Mode() {
        return S2Mode;
    }

    public void setS2Mode(String s2Mode) {
        S2Mode = s2Mode;
    }

    public String getS2Bank() {
        return S2Bank;
    }

    public void setS2Bank(String s2Bank) {
        S2Bank = s2Bank;
    }

    public String getS2Branch() {
        return S2Branch;
    }

    public void setS2Branch(String s2Branch) {
        S2Branch = s2Branch;
    }

    public String getS2Date() {
        return S2Date;
    }

    public void setS2Date(String s2Date) {
        S2Date = s2Date;
    }

    public String getS2Amount() {
        return S2Amount;
    }

    public void setS2Amount(String s2Amount) {
        S2Amount = s2Amount;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
        //System.out.println(Title);
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgreementID() {
        return agreementID;
    }

    public void setAgreementID(String agreementID) {
        this.agreementID = agreementID;
    }

    public void setJSONResponse()
    {

        //JSONRes = "Title:"+Title;
        //System.out.println(JSONRes);
    }
    public String getJSONResponse()
    {
        String tempJSONRes="{"+"\n\t"+"\"Title\":"  + "\"" +Title+ "\""+ ",\n\t" +"\"Name\":"+"\"" +Name+ "\""+ ",\n\t"
                +"\"Street\":"  + "\"" +Street+ "\""+ ",\n\t" +"\"City\":"+"\"" +City+ "\""+ ",\n\t"
                +"\"State\":"  + "\"" +State+ "\""+ ",\n\t" +"\"Zip\":"+"\"" +Zip+ "\""+ ",\n\t"
                +"\"CustomerId\":"  + "\"" +CustomerId+ "\""+ ",\n\t" +"\"CCNo\":"+"\"" +CCNo+ "\""+ ",\n\t"
                +"\"Segment\":"  + "\"" +Segment+ "\""+ ",\n\t" +"\"Limit\":"+"\"" +Limit+ "\""+ ",\n\t"
                +"\"Outstanding\":"  + "\"" +Outstanding+ "\""+ ",\n\t" +"\"SettlementAmt\":"+"\"" +Settlement+ "\""+ ",\n\t"
                +"\"DueDate\":"  + "\"" +DueDate+ "\""+ ",\n\t" + "\"Settlements\":"+"[\n\t\t{\n\t" +
                "\"SrNo\":"+"\"" +S1No+ "\""+ ",\n\t"
                +"\"Mode Of Payment\":"  + "\"" +S1Mode+ "\""+ ",\n\t" +"\"Bank Name\":"+"\"" +S1Bank+ "\""+ ",\n\t"
                +"\"Branch\":"  + "\"" +S1Branch+ "\""+ ",\n\t" +"\"Due Date\":"+"\"" +S1Date+ "\""+ ",\n\t"
                +"\"Amount\":"  +"\"" +S1Amount+ "\""+ "\n\t" +"},\n{\n\t" +"\"SrNo\":"+"\"" +S2No+ "\""+ ",\n\t"
                +"\"Mode Of Payment\":"  + "\"" +S2Mode+ "\""+ ",\n\t" +"\"Bank Name\":"+"\"" +S2Bank+ "\""+ ",\n\t"
                +"\"Branch\":"  + "\"" +S2Branch+ "\""+ ",\n\t" +"\"Due Date\":"+"\"" +S2Date+ "\""+ ",\n\t"
                +"\"Amount\":"  + "\"" +S2Amount+ "\""+"\n}\n]\n}\n";

        System.out.println(JSONRes);

        return tempJSONRes;

    }

}
