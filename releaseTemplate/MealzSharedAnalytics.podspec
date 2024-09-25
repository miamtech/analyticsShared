
Pod::Spec.new do |spec|
    spec.name              = 'MealzSharedAnalytics'
    spec.version           = '##VERSION##'
    spec.summary           = 'Mealz Shared Analytics for iOS'
    spec.homepage          = 'https://www.mealz.ai'
       spec.description           = <<-DESC
       Mealz Shared Analytics for iOS.
       DESC
    spec.author            = { 'Diarmuid McGonagle, Antonin François' => 'it@miam.tech' }
    spec.license           = { :type => 'GPLv3', :file => 'LICENSE' }
    spec.swift_versions = "5.8"
    spec.platform          = :ios, "12.0"
    spec.source            = { :http => 'https://github.com/miamtech/MealzSharedAnalyticsRelease/raw/release/##VERSION##/mealzSharedAnalytics.zip' }
    spec.ios.vendored_frameworks = 'mealzSharedAnalytics.xcframework'
end
