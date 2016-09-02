var gulp = require('gulp'),
    newer = require('gulp-newer'),
    sass = require('gulp-ruby-sass'),
    watch = require('gulp-watch'),
    plumber = require('gulp-plumber'),
    csso = require('gulp-csso'),
    jshint = require('gulp-jshint'),
    stylish = require('jshint-stylish'),
    gutil = require('gulp-util'),
    prefix = require('gulp-autoprefixer'),
    htmlmin = require('gulp-htmlmin'),
    header = require('gulp-header'),
    uglify = require('gulp-uglify'),
    ngannotate = require('gulp-ng-annotate'),
    concat = require('gulp-concat'),
    clean = require('gulp-clean'),
    rename = require('gulp-rename'),
    notify = require('gulp-notify'),
    preprocess = require('gulp-preprocess'),
    template = require('gulp-template'),
    tmplcache = require('gulp-angular-templatecache'),
    shell = require('gulp-shell'),
    proxy = require('proxy-middleware'),
    refresh = require('gulp-livereload'),
    karma = require('karma').Server,
    protractor = require('gulp-protractor').protractor,
    package = require('./package.json'),
    url = require('url'),
    express = require('express'),
    livereload = require('connect-livereload'),
    argv = require('yargs').argv,
    linker = require('gulp-linker'),
    env = require('gulp-env'),
    changed = require('gulp-changed');

var config = {
    serverport: 4000
};


var paths = {
    templates: 'public/src/templates/**/*.html',
    markup: [
        'public/src/**/*.html',
        '!public/src/templates/**/*.html'
    ],
    scripts: [
        'public/src/js/templates/templates.js',
        'public/src/js/tour.js',
        'public/src/js/app.js',
        'public/src/js/main.js',
        'public/src/js/factories/**/*.js',
        'public/src/js/directives/**/*.js',
        'public/src/js/controllers/**/*.js'
    ],
    libs: [
        'public/libs/es5-shim/es5-shim.js',
        'public/libs/lodash/lodash.js',
        'public/libs/jquery/dist/jquery.js',
        'public/libs/jquery-ui-sortable/jquery-ui-sortable.js',
        'public/libs/jq.isotope/jquery.isotope.js',
        'public/libs/angular/angular.js',
        'public/libs/slick-carousel/slick/slick.js',
        'public/libs/angular-isotope/dist/angular-isotope.js',
        'public/libs/angular-sanitize/angular-sanitize.js',
        'public/libs/angular-ui-router/release/angular-ui-router.js',
        'public/libs/angular-ui-sortable/sortable.js',
        'public/libs/angular-loading-bar/build/loading-bar.js',
        'public/libs/angular-animate/angular-animate.js',
        'public/libs/bootstrap-tour/build/js/bootstrap-tour-standalone.js',
        'public/libs/i18next/i18next.js',
        'public/libs/ng-i18next/dist/ng-i18next.js',
        'public/libs/dropzone/downloads/dropzone.min.js',
        'public/libs/sweetalert/lib/sweet-alert.js',
        'public/libs/velocity/velocity.js',
        'public/libs/add-to-homescreen/src/addtohomescreen.min.js',
        'public/libs/angular-scroll-glue/src/scrollglue.js',
        'public/libs/angular-google-maps/dist/angular-google-maps.js',
        'public/libs/spiderfier/spiderfier.js',
        'public/libs/echarts/build/source/echarts-all.js',
        'public/libs/angular-pageslide-directive/dist/angular-pageslide-directive.js',
        'public/libs/bowser/bowser.js',
        'public/libs/moment/min/moment.min.js'
    ],
    test_files: [
        'public/test/unit/controllers/**/*.js',
        'public/test/unit/factory/**/*.js',
        'public/test/unit/directives/**/*.js'
    ],
    unit_test_files: [
        'public/libs/angular-mocks/angular-mocks.js',
        'public/test/unit/global-vars.js'
    ],
    build_test_files: [
        'public/dist/js/lib.min.js',
        'public/test/unit/global-vars.js',
        'public/libs/angular-mocks/angular-mocks.js',
        'public/dist/js/app.min.js'
    ],
    styles: 'public/src/scss/**/*.scss',
    theme: 'public/src/packages/' + target + '/*.scss',
    fonts: ['public/src/packages/default/fonts/**/*', 'public/src/packages/' + target + '/fonts/**/*'],
    images: ['public/src/packages/default/img/**/*', 'public/src/packages/' + target + '/img/**/*'],
    locales: ['public/src/packages/default/locales/**/*', 'public/src/packages/' + target + '/locales/**/*'],
    tour: 'public/src/packages/' + target + '/tour.js',
    manifest: 'public/src/packages/' + target + '/manifest.json',
    mainLanguageFile: './public/src/packages/' + target + '/locales/en/translation.json'
};


paths.scriptsDev = paths.scripts.concat([
    'public/src/js/mocks/**/*.js',
])

var scriptsToInject = paths.scriptsDev.map(function(path) {
    return path.replace('public/src/', 'public/dist/');
});

var libToInject = ['public/dist/js/lib.min.js'];

var banner = ['/**',
    ' * <%= package.name %>',
    ' * <%= package.description %>',
    ' * Compiled: ' + Date(),
    ' * @version v<%= package.version %>',
    ' * @link <%= package.homepage %>',
    ' * @copyright <%= package.license %>',
    ' */',
    ''
].join('\n');


var dev = process.argv[2] === undefined ? true : false;
var prod = !dev;

/**
 * Base tasks
 */
gulp.task('server', function() {
    var server = express();
    server.use(livereload());
    server.use(express.static('public/dist'));
    server.listen(config.serverport);
});

gulp.task('copy', function() {
    return gulp.src(paths.fonts)
        .pipe(gulp.dest('public/dist/fonts'));
});

gulp.task('html:dev', function() {
    var lang = require(paths.mainLanguageFile);
    return gulp.src(paths.markup)
        .pipe(preprocess({
            context: {
                dev: true,
                MAP_KEY: lang.mapsApiKey
            }
        }))
        .pipe(gulp.dest('public/dist'));
});

gulp.task('clean', function() {
    return gulp.src('public/dist/', {
            read: false
        })
        .pipe(clean());
});

gulp.task('export', function() {
    return gulp.src(['public/dist/**/*'])
        .pipe(gulp.dest('src/main/webapp'));
});

gulp.task('watch', function() {
    ///var scripts = paths.scripts.slice(0, paths.scripts.length - 1);
    refresh.listen();
    gulp.watch(paths.markup, ['html:dev', 'inject:dev']);
    gulp.watch([paths.styles, paths.theme], ['styles:dev']);
    gulp.watch(paths.scripts, ['scripts:dev', 'inject:dev']);
    gulp.watch(paths.images, ['images']);
    gulp.watch(paths.locales).on('change', refresh.changed);
    gulp.watch(paths.tour, ['scripts:dev']);
    gulp.watch(paths.templates, ['scripts:dev', 'inject:dev']);
});


/**
 * Dev builds
 */
gulp.task('copylibs:dev', function() {
    return gulp.src(paths.libs)
        .pipe(concat('all.min.js'))
        .pipe(gulp.dest('public/dist/js'));
});

gulp.task('scripts:dev', ['copyTour', 'copyManifest'], function() {
    var dist = 'public/dist/js';
    var lang = require(paths.mainLanguageFile);
    env({
        vars: {
            MAPS_KEY: lang.mapsApiKey
        }
    });
    return gulp.src(paths.scriptsDev, {
            base: 'public/src/js'
        })
        .pipe(preprocess({
            context: {
                dev: false
            }
        }))
        .pipe(changed(dist))
        .pipe(gulp.dest(dist));
});

gulp.task('inject:dev', ['scripts:dev', 'html:dev'], function() {
    return gulp.src('public/dist/index.html')
        .pipe(linker({
            scripts: [libToInject, scriptsToInject],
            appRoot: 'public/dist/'
        }))
        .pipe(gulp.dest('public/dist/'))
        .pipe(refresh());
});

gulp.task('styles:dev', function() {
    return gulp.src(paths.theme)
        .pipe(plumber())
        .pipe(sass({
            unixNewlines: true
        }))
        .pipe(prefix('last 1 version', '> 1%', 'ie 8', 'ie 7'))
        .on('error', notify.onError())
        .pipe(rename('styles.css'))
        .pipe(gulp.dest('public/dist'))
        .pipe(refresh());
});

/**
 * Prod builds
 */
gulp.task('copylibs:prod', function() {
    return gulp.src(paths.libs)
        .pipe(concat('lib.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('public/dist/js'));
});

gulp.task('scripts:prod', ['tmplcache', 'copyTour'], function() {
    var lang = require(paths.mainLanguageFile);
    env({
        vars: {
            MAPS_KEY: lang.mapsApiKey
        }
    });
    return gulp.src(paths.scripts)
        .pipe(preprocess({context: {
            dev: false
        }}))
        .pipe(concat('app.min.js'))
        .pipe(ngannotate())
        .pipe(uglify())
        .pipe(header(banner, {
            package: package
        }))
        .pipe(gulp.dest('public/dist/js'));
});

gulp.task('styles:prod', function() {
    return gulp.src(paths.theme)
        .pipe(plumber())
        .pipe(sass({
            unixNewlines: true,
            style: 'compressed'
        }))
        .pipe(prefix('last 1 version', '> 1%', 'ie 8', 'ie 7'))
        //  .pipe(csso())
        .pipe(header(banner, {
            package: package
        }))
        .on('error', notify.onError())
        .pipe(rename('styles.css'))
        .pipe(gulp.dest('public/dist'));
});

gulp.task('html:prod', function() {
    return gulp.src(paths.markup)
        .pipe(preprocess({
            context: {
                dev: false
            }
        }))
        .pipe(htmlmin({
            collapseWhitespace: true,
            removeComments: true,
            removeRedundantAttributes: true,
            removeAttributeQuotes: true,
            removeEmptyAttributes: true
        }))
        .pipe(gulp.dest('public/dist'));
});
/**
 * Test tasks
 */

gulp.task('test:build', function(done) {
    new karma({
        configFile: __dirname + '/public/test/unit/karma.conf.js',
        singleRun: true
    }, done).start();
});

gulp.task('dev:ut', function(done) {
    new karma({
        configFile: __dirname + '/public/test/unit/karma.conf.js',
        autoWatch: true,
        singleRun: false
    }, done).start();
});

gulp.task('test:e2e', function() {
    return gulp.src(['public/test/e2e/*.spec.js'])
        .pipe(protractor({
            configFile: 'public/test/e2e/protractor.conf.js'
        }));
});


/**
 * Tasks list
 */
gulp.task('default', [
    'html:dev',
    'scripts:dev',
    'copy',
    'copylibs:dev',
    'styles:dev',
    'images',
    'locales',
    'inject:dev',
    'server',
    'watch'
]);

gulp.task('build', [
    'html:prod',
    'scripts:prod',
    'copy',
    'copylibs:prod',
    'images',
    'locales',
    'styles:prod'
]);

