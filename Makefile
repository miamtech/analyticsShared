VERSION ?= 0.0.0
BUILD ?= local

# ---------------------------------------- COMMON ----------------------------------------

# If BUILD is release, add replace_version_number to the list of tasks to execute
ifeq ($(BUILD),release)
    ALL_TASKS = replace_version_number js ios android restore_version_number
else ifeq ($(BUILD),local)
    ALL_TASKS = js ios android
else
    $(error Invalid value for BUILD: $(BUILD). Please use 'local' or 'release')
endif

all: $(ALL_TASKS)

replace_version_number:
	sed -i '' 's/"##VERSION##"/"$(VERSION)"/' mealz-shared-analytics/src/commonMain/kotlin/mealz/ai/AbstractSharedAnalytics.kt

restore_version_number:
	git restore mealz-shared-analytics/src/commonMain/kotlin/mealz/ai/AbstractSharedAnalytics.kt

# ------------------------------------------ JS ------------------------------------------
build_js_output:
	./gradlew jsBrowserProductionWebpack

build_dist_folder:
	cd mealz-shared-analytics && \
	ruby scripts/generate_ts_fun_declarations.rb && \
	ruby scripts/generate_ts_types_declarations.rb && \
	cp build/kotlin-webpack/js/productionExecutable/main.js dist/main.js && \
	sed 's/"##VERSION##"/"$(VERSION)"/' build/processedResources/js/main/package.json > dist/package.json && \
	cd ..

js: build_js_output build_dist_folder

# ----------------------------------------- IOS ------------------------------------------
assemble_xcframework:
	./gradlew clean && \
	./gradlew assemblexcframework

ios: assemble_xcframework

# --------------------------------------- ANDROID ----------------------------------------

android:
	./gradlew assembleRelease
