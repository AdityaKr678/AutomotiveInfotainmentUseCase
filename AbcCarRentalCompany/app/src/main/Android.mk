# Copyright (C) 2024 Aditya Kumar

LOCAL_PATH := $(call my-dir)

########################################
# Build the AbcCarRentalCompany. #
########################################
include $(CLEAR_VARS)

LOCAL_MANIFEST_FILE := AndroidManifest.xml
LOCAL_SRC_FILES := $(call all-java-files-under, java)
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res


LOCAL_PACKAGE_NAME := AbcCarRentalCompany
LOCAL_MODULE_TAGS := optional
LOCAL_PRIVILEGED_MODULE := true
LOCAL_PRODUCT_MODULE := true
LOCAL_PRIVATE_PLATFORM_APIS := true
LOCAL_CERTIFICATE := platform
