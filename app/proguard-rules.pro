# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/Shared/android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# App Specific
-keep class jp.edainc.androidsamplesjava.data.api.responses.** { *; }
-keep class jp.edainc.androidsamplesjava.model.** { *; }

# RetroLambda
-dontwarn java.lang.invoke.*

# Okio(OkHttp)
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Gson
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepattributes *Annotation*
-keepattributes EnclosingMethod

# Dagger2
-dontwarn com.google.errorprone.annotations.DoNotMock
-dontwarn com.google.errorprone.annotations.ForOverride
-dontwarn com.google.errorprone.annotations.CanIgnoreReturnValue

# Orma なぜかこいつにnotifyDatasetChangedが無いと言われる。使わなければ大丈夫だと思う
# or 使わないと警告が出る？
-dontwarn com.github.gfx.android.orma.widget.OrmaRecyclerViewAdapter

# Picasso
-dontwarn com.squareup.okhttp.**