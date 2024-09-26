VERSION ?= 0.0.0
ENV ?= dev

# Ensure ENV is either 'dev' or 'prod'
ifeq ($(ENV),dev)
    BUILD_TYPE = debug
else ifeq ($(ENV),prod)
    BUILD_TYPE = release
else
    $(error Invalid value for ENV: $(ENV). Please use 'dev' or 'prod'.)
endif

all: js ios android

# ------------------------------------------ JS ------------------------------------------
build_js_output:
	./gradlew jsBrowserProductionWebpack

build_dist_folder:
	cd mealzSharedAnalytics && \
	ruby scripts/generate_ts_fun_declarations.rb && \
	ruby scripts/generate_ts_types_declarations.rb && \
	cp build/kotlin-webpack/js/productionExecutable/main.js dist/main.js && \
	echo "Using version $(VERSION)" && \
	sed 's/"##VERSION##"/"$(VERSION)"/' build/processedResources/js/main/package.json > dist/package.json && \
	cd ..

js: build_js_output build_dist_folder

# ----------------------------------------- iOS ------------------------------------------
assemble_xcframework:
	./gradlew clean && \
	./gradlew assemblexcframework

copy_xcframework_to_sources:
	cp -r mealzSharedAnalytics/build/XCFrameworks/$(BUILD_TYPE)/mealzSharedAnalytics.xcframework ./Sources

ios: assemble_xcframework copy_xcframework_to_sources

# --------------------------------------- Android ----------------------------------------

android:
	./gradlew assembleRelease
