import java.util.regex.Pattern.compile

plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"


        multiDexEnabled =true

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
//    val room_version  = "2.6.0"



    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.10.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.tracing:tracing:1.1.0")
    testImplementation("junit:junit:4.13.2")

    compile ("com.android.support:multidex:1.0.1")



    androidTestImplementation("androidx.test.ext:junit:1.2.0-alpha01")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.amplifyframework:aws-api:1.33.0")
    implementation ("com.amplifyframework:aws-datastore:1.35.3")
    implementation ("com.amplifyframework:aws-datastore:1.33.0")
}