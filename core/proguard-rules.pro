-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

-dontwarn net.sqlcipher.**
-keep class net.sqlcipher.** { *; }
-dontwarn org.conscrypt.**
-keep class org.conscrypt.** { *; }


##---------------Begin: proguard configuration for Gson ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
    @com.google.gson.annotations.SerializedName <fields>;
}


##---------------Begin: proguard configuration for Retrofit ----------
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore StringConcatFactory.
-dontwarn java.lang.invoke.StringConcatFactory

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

-keepnames class kotlinx.** { *; }

-keep class com.example.core.data.Resource { *; }

-keep class com.example.core.ui.MovieAdapter { *; }

-keep class com.example.core.data.repository.MovieRepository { *; }

# Keep the entire domain and use case classes to avoid obfuscation or method removal
-keep class com.example.core.domain.usecase.MovieUseCase { *; }
-keep class com.example.core.domain.repository.IMovieRepository { *; }
-keep class com.example.core.utils.AppExecutors { *; }

-keep class kotlinx.coroutines.flow.* { *; }
-keepclassmembers class kotlinx.coroutines.flow.Flow { *; }
-keepclassmembers class kotlinx.coroutines.CoroutineScope { *; }

-keep class com.example.core.utils.DataMapper { *; }
-keep class com.example.core.utils.DataManipulate { *; }

-keep class *Test { *; }


##---------------Begin: proguard configuration for Glide ----------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
    <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
    **[] $VALUES;
public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
    *** rewind();
}

# Keep Hilt components
-keep class * implements dagger.hilt.internal.GeneratedComponent { *; }
-keep class * implements dagger.hilt.internal.GeneratedComponentManager { *; }
-keep class * implements dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }

# Keep classes with @Inject constructors
-keepclasseswithmembernames class * {
    @javax.inject.Inject <init>(...);
}

# Keep Hilt Android classes (for projects using Hilt with Android)
-keep class dagger.hilt.android.** { *; }
-keep interface dagger.hilt.android.** { *; }

# Keep classes annotated with @HiltAndroidApp, @AndroidEntryPoint, etc.
-keep @dagger.hilt.android.HiltAndroidApp class *
-keep @dagger.hilt.android.AndroidEntryPoint class *
-keep @dagger.hilt.InstallIn class *
-keep @dagger.Module class *
-keep @dagger.hilt.components.SingletonComponent class *
-keep @dagger.hilt.android.components.ActivityComponent class *
-keep @dagger.hilt.android.components.FragmentComponent class *

# Keep classes with @Module annotations
-keep @dagger.Module class * { *; }

# Keep classes with @Provides annotations
-keep class * {
    @dagger.Provides <methods>;
}

-keep class com.example.movieku.** { *; }
-keep class com.example.core.domain.usecase.** { *; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

-keep class com.example.core.data.source.local.** { *; }
-keepclassmembers class com.example.core.data.source.local.** { *; }

-keep class com.example.core.data.source.remote.response.** { *; }
-keepclassmembers class com.example.core.data.source.remote.response.** { *; }

-keep class com.example.core.di.** { *; }
-keepclassmembers class com.example.core.di.** { *; }

-keep class com.example.core.domain.usecase.** { *; }
-keepclassmembers class com.example.core.domain.usecase.** { *; }

-keep class com.example.core.ui.** { *; }
-keepclassmembers class com.example.core.ui.** { *; }

-keepclassmembers class ** {
    *** get*(...);
    *** set*(...);
}

-keepclassmembers class ** {
    public <init>(...);
}

-keep class com.google.gson.** { *; }