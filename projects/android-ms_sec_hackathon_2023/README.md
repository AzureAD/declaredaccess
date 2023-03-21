# Android POC for DeclaredAccess

## What's here so far
- [brianmel] I have tried to make this project easy to bootstrap working with Graph & MSAL. See [`MainActivity.kt`](https://github.com/AzureAD/declaredaccess/blob/dev/projects/android-ms_sec_hackathon_2023/app/src/main/java/com/sample/hackathon/declaredaccessandroid/MainActivity.kt#L11-L12) for comments with pointers on useful factory functions to call.
- [brianmel] I've created an app reg in my own tenant. It will work with MSA or enterprise accounts.

## You will need to configure a keystore to successfully build
- [brianmel] Here is the keystore you will need. Put it at `C://Users/<your_username>/.android/debug.keystore`. You may need to reboot your IDE for the changes to be picked up.
