plugins {
    id("com.android.application")
    id("com.google.gms.google-services") version "4.4.1"
}


android {
    namespace = "edu.northeastern.demostructure"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.northeastern.demostructure"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.17")
    implementation("io.github.florent37:shapeofview:1.4.7")
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.19")
    implementation(platform("com.google.firebase:firebase-bom:32.7.3"))
    implementation("com.google.firebase:firebase-analytics")
    implementation(platform("com.google.firebase:firebase-bom:32.7.3"))
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.gms:google-services:4.2.0")
    implementation("com.google.firebase:firebase-core:9.6.1")
    implementation("com.jaredrummler:material-spinner:1.3.1")
}