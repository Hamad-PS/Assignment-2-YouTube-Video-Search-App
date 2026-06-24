<div align="center">

# 🎬 YouTube Video Search App

### Search YouTube and browse results in a clean Android list

A simple Android app that searches videos through the **YouTube Data API v3**
and displays them in a `RecyclerView` — built in **Java**.

<br>

![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-2.9.0-48B983?style=for-the-badge)
![License](https://img.shields.io/badge/License-Educational-blue?style=for-the-badge)

</div>

---

## ✨ Features

- 🔍 Search YouTube videos by keyword
- 📋 Results in a scrollable `RecyclerView`
- 🖼️ Thumbnails loaded with **Glide**
- ⏳ Loading indicator while fetching
- ⚠️ Clear messages for empty input, no internet, no results, and API errors
- ⌨️ Keyboard **Search** key triggers the search
- 🧹 Old results clear automatically on a new search

---

## 🛠️ Tech Stack

<div align="center">

| Purpose | Library |
| :--- | :--- |
| Language | Java |
| Networking | Retrofit 2 |
| JSON Parsing | Gson Converter |
| List Display | RecyclerView |
| Image Loading | Glide |
| HTTP Logging | OkHttp Logging Interceptor |
| UI | AndroidX + Material |

</div>

---

## 📱 Screenshots

<div align="center">

<!-- Replace with your own images -->
<img src="screenshots/search.png" width="30%" />
&nbsp;&nbsp;
<img src="screenshots/results.png" width="30%" />

</div>

---

## 📂 Project Structure

```
app/src/main/
├── AndroidManifest.xml
├── java/com/example/youtubesearch/
│   ├── MainActivity.java          # Main screen: input, search flow, UI states
│   ├── VideoAdapter.java          # RecyclerView adapter for the video list
│   ├── VideoItem.java             # Clean model used by the adapter
│   ├── network/
│   │   ├── ApiClient.java         # Retrofit singleton
│   │   └── YouTubeApiService.java # Retrofit endpoint interface
│   └── model/                     # Raw API response models
│       ├── YouTubeResponse.java
│       ├── SearchItem.java
│       ├── VideoId.java
│       ├── Snippet.java
│       ├── Thumbnails.java
│       └── Thumbnail.java
└── res/layout/
    ├── activity_main.xml
    └── item_video.xml
```

---

## ⚙️ How It Works

```
User types query  →  Validate input & network  →  Retrofit request
        →  YouTube returns JSON  →  Gson maps it to objects
        →  Convert to VideoItem  →  Show in RecyclerView
```

The `ProgressBar` shows during loading and hides once a final state
(results, a message, or an error) is reached.

---

## 🚀 Getting Started

### Prerequisites

- Android Studio (latest stable version)
- A device or emulator on **API 21 (Android 5.0)** or higher
- A YouTube Data API v3 key

### Installation

```bash
git clone https://github.com/<your-username>/youtube-search-app.git
```

1. Open the project in Android Studio and let Gradle sync.
2. Add your API key (see below).
3. Run on a device or emulator.

### 🔑 API Key

Set your key in `MainActivity.java`:

```java
private static final String API_KEY = "YOUR_API_KEY_HERE";
```

Create a key from the [Google Cloud Console](https://console.cloud.google.com/)
by enabling the **YouTube Data API v3**.

> [!NOTE]
> Hardcoding a key is fine for an assignment. For a real app, restrict the key
> in the Google Cloud Console and keep it out of version control
> (e.g. in `local.properties`).

---

## 📖 Usage

1. Launch the app.
2. Type a search term (e.g. `android tutorial`).
3. Tap **Search** or press the keyboard search key.
4. Browse the results.

---

## 📝 Notes

- The `medium` thumbnail size is used; if missing, the image space stays blank instead of crashing.
- Publish dates are shortened to `YYYY-MM-DD` for readability.
- The YouTube API has a daily quota — if searches stop working, you may have hit the limit.

---

<div align="center">

Made for educational purposes 🎓 — feel free to use it as a learning reference.

</div>
