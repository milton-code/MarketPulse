# MarketPulse

This Android application implements a custom splash screen, followed by an onboarding screen that briefly explains the app's purpose.

The app implements user authentication via Firebase Authentication, using user credentials (email and password) as the sign-in method. Upon logging in for the first time, the app requests runtime permission to receive notifications.

Notification management is implemented using Firebase Cloud Messaging.

The app communicates with an API developed using Spring Boot (MarketPulseBackend) to store the FCM token in a database and enable personalized notifications for each user. The database used for the project was PostgreSQL running in a Docker container.

## Key Features

- Clean architecture with MVVM design pattern
- Uses the Retrofit library to consume a REST API
- Uses Flows and coroutines to manage a reactive UI
- Handles exceptions such as connection errors
- Requests runtime permissions for notifications
- Authentication via Firebase Authentication
- Implementation of a splash screen
- Implementation of an onboarding screen
- Uses Dagger-Hilt for dependency injection.

## Tech Stack

**Client:** Kotlin, Jetpack Compose, Material Design, Retrofit, Flows, Coroutines, JSON, Gradle, Dagger - Hilt

**Server:** SpringBoot REST API (MarketPulseBackend), Firebase Authentication, Firebase Cloud Messaging, Docker, PostgreSQL

**Architectural Pattern:** Clean architecture with Model - View - ViewModel design pattern 


## Screenshots

![App Screenshot](https://github.com/milton-code/MarketPulse/blob/main/images/1.jpeg)
![App Screenshot](https://github.com/milton-code/MarketPulse/blob/main/images/1-1.jpeg)
![App Screenshot](https://github.com/milton-code/MarketPulse/blob/main/images/1-2.jpeg)
