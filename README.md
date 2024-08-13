# My Android Project

Welcome to my Android project! This project aims to provide a modern and user-friendly mobile application experience with enhanced features and a clean design. Below you'll find details about the project's features, setup instructions, and contribution guidelines.

## Features

### 1. Splash Screen
- **Design Update**: Revamped splash screen to match the latest minimalist design trends. It features a clean white background and a prominently centered logo.

### 2. Enhanced Login Functionality
- **Input Validation**: Users can choose between entering a phone number or an email. The app validates phone numbers based on specific prefixes and ensures email addresses end with `@gmail.com`.
- **Password Validation**: Enforces strict criteria:
  - Minimum length of 8 characters and maximum length of 64 characters.
  - Allows ASCII and Unicode characters.
  - Checks against a list of commonly used or compromised passwords.
  - Validates against repetitive or sequential characters.
  - Limits consecutive failed authentication attempts to 100.

### 3. Modernized Layout
- **Login and Registration Pages**: Updated design for a more polished and user-friendly interface.

## Getting Started

To get started with this project, follow these instructions:

### Prerequisites
- Android Studio (latest version)
- Java 8 or higher
- Gradle

### Installation

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/your-repo.git
