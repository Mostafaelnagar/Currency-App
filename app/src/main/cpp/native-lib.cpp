//
// Created by t-e-s on ٢٠‏/٦‏/٢٠٢٢.
//

#include <string>
#include <jni.h>


extern "C"
JNIEXPORT jstring JNICALL
Java_app_te_currency_1app_data_remote_Keys_releaseBaseUrl(JNIEnv *env, jobject thiz) {
    std::string releaseBaseUrl = "https://api.apilayer.com/fixer/";
    return env->NewStringUTF(releaseBaseUrl.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_app_te_currency_1app_data_remote_Keys_apiKey(JNIEnv *env, jobject thiz) {
    std::string apiKey = "vhbnf5vBAy2kMM2UICcoIX3jDia53JPP";
    return env->NewStringUTF(apiKey.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_app_te_currency_1app_data_remote_Keys_roomDbName(JNIEnv *env, jobject thiz) {
    std::string room_name = "currency_db";
    return env->NewStringUTF(room_name.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_app_te_currency_1app_data_remote_Keys_appDataStore(JNIEnv *env, jobject thiz) {
    std::string app_store_name = "app_store_name";
    return env->NewStringUTF(app_store_name.c_str());
}