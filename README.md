# LT_Java_Appium
Sample repo to run app automation on real device on LambdaTest.


**Below is the curl request to upload the app for automation**

```
curl --location --request POST 'https://manual-api.lambdatest.com/app/upload/realDevice' \
--header 'Authorization: Basic <ENTER_BASIC_AUTH_TOKEN_HERE>' \
--form 'name="lambda1"' \
--form 'appFile=@"/path/to/file"'
```