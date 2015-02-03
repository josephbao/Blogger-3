modules = {
    application {
        resource url:'js/application.js'
    }
    angularjs {
        resource url: 'js/angular-1.3.9.js', disposition: 'head'
    }
    baseCss {
        resource url: '/css/main.css'
    }
    core {
        dependsOn 'baseCss'
        dependsOn 'angularjs'
        resource url: 'js/blogger.js'
    }
}