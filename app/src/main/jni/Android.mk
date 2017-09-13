#LOCAL_PATH := $(call my-dir)
#include $(CLEAR_VARS)
#LOCAL_MODULE := TestNdk
#LOCAL_SRC_FILES := com_ione_opustool_OpusTool.c
#include $(BUILD_SHARED_LIBRARY)

LOCAL_PATH := $(call my-dir)  #加载当前路径
include $(CLEAR_VARS)
include $(LOCAL_PATH)/celt_sources.mk   #加载celt 所有.c的 mk
include $(LOCAL_PATH)/silk_sources.mk  #加载silk 所有.c 的mk
include $(LOCAL_PATH)/opus_sources.mk #加载opus 所有.c 的mk
MY_MODULE_DIR       := newopus  #库的名称
LOCAL_MODULE        := $(MY_MODULE_DIR)
SILK_SOURCES += $(SILK_SOURCES_FIXED)
#编译的源代码.c
CELT_SOURCES += $(CELT_SOURCES_ARM)
SILK_SOURCES += $(SILK_SOURCES_ARM)
LOCAL_SRC_FILES     := Opusmain.c $(CELT_SOURCES) $(SILK_SOURCES) $(OPUS_SOURCES)

#LOCAL_LDLIBS        := -lm –llog  #加载系统的库 日志库

LOCAL_C_INCLUDES    := \
$(LOCAL_PATH)/include \
$(LOCAL_PATH)/silk \
$(LOCAL_PATH)/silk/fixed \
$(LOCAL_PATH)/celt
#附加编译选项
LOCAL_CFLAGS        := -DNULL=0 -DSOCKLEN_T=socklen_t -DLOCALE_NOT_USED -D_LARGEFILE_SOURCE=1 -D_FILE_OFFSET_BITS=64
LOCAL_CFLAGS        += -Drestrict='' -D__EMX__ -DOPUS_BUILD -DFIXED_POINT=1 -DDISABLE_FLOAT_API -DUSE_ALLOCA -DHAVE_LRINT -DHAVE_LRINTF -O3 -fno-math-errno
LOCAL_CPPFLAGS      := -DBSD=1
LOCAL_CPPFLAGS      += -ffast-math -O3 -funroll-loops
include $(BUILD_SHARED_LIBRARY)  #编译动态库设置