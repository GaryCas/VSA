var gulp = require('gulp');

var config = {
    serverport: 4000
};

var target = argv.target || 'default';

var paths = {

}


paths.scriptsDev = paths.scripts.concat([
    'public/src/js/mocks/**/*.js',
])

var scriptsToInject = paths.scriptsDev.map(function(path) {
    return path.replace('public/src/', 'public/dist/');
});

var libToInject = ['public/dist/js/lib.min.js'];


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