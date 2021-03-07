# M1 Secure Development : Mobile applications

I created an app by Android Studio with Java
Here is the login UI, user should enter the right name and the right password.
![S10307-19071205](S10307-19071205.jpg)
If user entered wrong account, it will show failed to login:
![S10307-19151914](S10307-19151914.jpg)
Or, we will get the data of his account by using the API url, the we show a message of welcome:
![S10307-19180089](S10307-19180089.jpg)
So we can see the menu of the bank app.
We can add or delete a text of operation in the interface. Such as date and the balance in the account.
There is also a button to update his account.

Q-Explain how you ensure user is the right one starting the app
A-I create a interface with a fonction of login, only the user who has the right name and password can access to the account.
- ![Markor_2021-03-07T19-28-48](Markor_2021-03-07T19-28-48.jpg)

Q-How do you securely save user's data on your phone ?
A-To ensure that this app can be used offline, we should have a database to save user's data. But as we know that sql is easy to be seen and edited by others. Then we need a database with a password. So i used SqlChiper which can store a data with encrypted password.
![Markor_2021-03-07T19-25-15](Markor_2021-03-07T19-25-15.jpg)

Q-How did you hide the API url ?
A-To get the user's account we have to connect an API url. We dont want the url be seen by others, because there are the private information of client. So i used ProGuard to encrypt my code.
![Markor_2021-03-07T19-28-03](Markor_2021-03-07T19-28-03.jpg)
