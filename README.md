---

# YouTube WebView for Legacy Android Devices

A lightweight YouTube WebView wrapper built specifically for old Android tablets where the official YouTube app no longer runs and Google Play Services are unsupported. Ideal for Android 4.1 (Jelly Bean) and above.

## 🎯 Project Purpose

I had an old tablet lying around with Android 4.1 that couldn't run YouTube or most modern apps due to outdated Play Services. Rather than let it go to waste, I built this minimal WebView-based YouTube browser to let kids watch videos safely and easily.

It’s a simple solution from a developer’s perspective, but a huge usability upgrade for the kids at home—and a good way to reuse aging hardware.


## 📦 Download APK

> 📲 [Download Latest APK](https://www.mediafire.com/file/1m99mh8qaur5c6n/Youtube_Web.apk/file)
> 
No installation from Play Store required. Just download, allow unknown sources, and install directly on any compatible device (Android 4.1+).


## 🧑‍💻 Features

* Loads YouTube's web version inside a WebView
* Uses `LOAD_CACHE_ELSE_NETWORK` for better caching
* Displays loading progress with a custom ProgressBar
* Detects no internet connection and shows fallback message
* Supports DOM storage and HTML5 video features
* Maintains WebView state on orientation changes
* Handles back navigation within WebView

## 🛠️ Tech Stack

* **Language:** Kotlin
* **Min SDK:** 16 (Android 4.1 Jelly Bean)
* **UI:** Android WebView + Material ProgressBar

## 📲 How to Run

1. Clone this repository

   ```bash
   git clone https://github.com/Hasnain17/Youtube-Web.git
   ```
2. Open in Android Studio
3. Build and run on a physical device or emulator running Android 4.1+

## 📁 Folder Structure

```bash
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/app/hasnain/youtubeweb/
│   │   │   │   └── MainActivity.kt
│   │   │   ├── res/
│   │   │   │   └── layout/activity_main.xml
│   │   │   └── AndroidManifest.xml
```

## 📡 Future Improvements

* Add parental controls or content filters
* Bookmark support for favorite videos
* Offline video caching (if feasible)

## 🔗 GitHub Repository

> 📦 [GitHub Repo](https://github.com/Hasnain17/Youtube-Web.git)

---

