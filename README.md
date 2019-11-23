# README for Orphan Connect

## About
Orphan Connect is an Android application that allows for the secure donation of funds to directly benefit orphans affected by the ongoing civil war in Southern Cameroons. The Department of Health and Social Services in the Federal Republic of Ambazonia is cataloguing the orphans and providing basic information about them, which Orphan Connect displays in order to allow users to choose a child or even multiple children to donate to. Orphan Connect also hopes to raise awareness about the plight of the children in the Ambazonia, and serve as a conduit for help from well wishers across the world who are interested in sponsoring these children in school, providing food and clothing, and meeting other essential needs.

********************************************************************************

## Release Notes

### New Features
- Improved appearance of RecyclerView List of Orphans
- Added support for pictures in the profiles of orphans


### Bug Fixes
- 

### Known Bugs/Defects
- App is currently lacking an appropriate icon

********************************************************************************

## Installation Guide

### Pre-Requisites
1. Download and install the Java 8 JDK from [Oracle’s website](https://www.oracle.com/technetwork/java/javase/downloads/index.html). The current version at the time of writing is 8u231.
2. Download Android Studio and the Android SDK from the Android developer [website](https://developer.android.com/studio/).
3. Have a working Android device that can be used for testing or create a virtual emulated device by using the Android AVD manager. The [Android developer guide](https://developer.android.com/studio/run/managing-avds) has instructions on how to create a virtual device and instructions on how to troubleshoot the emulator if there are any issues.

### Library Dependencies
The following dependencies are used in the project, but they will be automatically downloaded when compiling and running the project via gradle:
- Android Lifecycle Components
- Android Room
- Braintree
- Dagger
- JUnit
- Kotlin
- Kotlin Coroutines
- Retrofit

### Download Instructions
Orphan Connect is stored on Github, and should be downloaded as follows:
- Navigate to the Terminal for Mac and Linux devices, or the CMD for Windows.
- Navigate to the directory that you wish to install the application to
- Enter the following command: `git clone https://github.com/Orphan-Connect-SCORE-Team/Orphan-Connect`
- Once installed, open Android Studio. Navigate to File → Open and select the project folder containing the cloned repository.

If you do not want to work off of our repository, you can also download the project as a zip file from [https://github.com/Orphan-Connect-SCORE-Team/Orphan-Connect/archive/master.zip](https://github.com/Orphan-Connect-SCORE-Team/Orphan-Connect/archive/master.zip).


### Build Instructions
Before anything else, we strongly recommend cleaning the project to prevent build errors from occurring. In Android Studio, navigate to Build → Clean Project to ensure that local file paths are not saved.

__Build and Run for Testing__
1. Navigate to the green play button at the top of the screen to run the program on the selected device.
2. Once clicked, the application will build, install, and run on the selected device (emulator or physical device).

__Build App to Install on Phone__
To create an app bundle or apk so that the app can be sent, emailed, downloaded, and installed on any Android device, go to Build → Build Bundle(s) / APK(s) → Build APK(s) or Build Bundle(s). Follow the on screen instructions and the resulting .aab or .apk file can be used to install the app on an Android phone.

### Installation
__Android 8.0+__

1. Download the Orphan-Connect .aab/.apk file from the build step onto the phone.
2. Select the file either from the notification panel or from a file explorer app to install.
3. Follow the on-screen instructions and allow the application to be installed.

__Android 7.x and lower__

1. Navigate to the settings app.
2. Select “Security”.
3. Under “Device Administration”, tap “Unknown Sources” to toggle it to on.
4. Download the Orphan-Connect .aab/.apk file from the build step onto the phone.
5. Select the file either from the notification panel or from a file explorer app to install.
6. Follow the on-screen instructions and allow the application to be installed.


### Run Instructions
Open the app by clicking on the app icon. Running the software on the device will depend on your Android device version. If you have a problem with running the app due to the Android version, see the installation step to make sure it was installed correctly.
