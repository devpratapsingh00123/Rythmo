# Rythmo

**Rythmo** — mood-based music player (Android, Kotlin)

## Features
- Mood selection and playlist generation
- Profile setup with image upload (gallery only)
- Playback using external links (YouTube/Spotify)
- Dark/light mode support
- Password strength and suggestions (if applicable)

## Tech stack
- Kotlin, Android SDK
- Jetpack: ViewModel, LiveData / Flow, Navigation, Room (if used)
- Retrofit / OkHttp for networking
- Gradle Kotlin DSL

## Setup
1. Clone repo  
2. `./gradlew clean assembleDebug`  
3. Open in Android Studio Arctic Fox or later  
4. If necessary, add your `google-services.json` (kept out of repo)

## Run tests
`./gradlew test`  // unit tests  
`./gradlew connectedAndroidTest`  // instrumentation tests (if available)

## Architecture
Short summary: MVVM + repository pattern / or Clean Architecture

## Contributing
PRs welcome. Please open issues for bugs/features.

## License
[MIT] — add LICENSE file
