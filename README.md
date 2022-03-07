# LT_Java_Appium
Sample repo to run app automation on real device on LambdaTest.


**Below is the curl request to upload the app for automation**

```
curl --location --request POST 'https://manual-api.lambdatest.com/app/upload/realDevice' \
--header 'Authorization: Basic <ENTER_BASIC_AUTH_TOKEN_HERE>' \
--form 'name="lambda1"' \
--form 'appFile=@"/path/to/file"'
```

### **Step 1: Upload Your Application**
1. You need to generate Basic Auth (BASE64) Token with your LambdaTest UserName & Access Key [Basic Authentication (Encode Credentials to Base 64) | API Connector](https://mixedanalytics.com/knowledge-base/api-connector-encode-credentials-to-base-64/).
    1. Enter your “Username:AccessKey” and click on **Base64 Encode**.
    2. A basic authentication token will be generated. Copy the token.
2. Use the following cURL command with your basic auth token in cmd/Terminal:
```bash
curl --location --request POST 'https://manual-api.lambdatest.com/app/upload/realDevice'
--header 'Authorization: Basic <ENTER_BASIC_AUTH_TOKEN>'
--form 'name="lambda1"'
--form 'appFile=@"/path/to/file"'
```

### **Step 2: Write Your Automation Script**
Write your automation script in the client language of your choice from the ones [supported by Appium](https://appium.io/downloads.html). In the sample automation script in Java for the sample app downloaded above. Ensure to update the `app_url`, `username` and `accesskey` in the below code.

### **Step 3: Execute Your Test Case**
Debug and run your code. Run iOSApp.java or AndroidApp.java in your editor.
```
mvn clean install -DsuiteXmlFile=src/test/java/Android.xml
```
```
mvn clean install -DsuiteXmlFile=src/test/java/IOS.xml
```


### **Step 4: View Test Execution**
Once you have run your tests, you can view the test execution along with logs. You will be able to see the test cases passing or failing. You can view the same at [LambdaTest Automation](https://accounts.lambdatest.com/login).