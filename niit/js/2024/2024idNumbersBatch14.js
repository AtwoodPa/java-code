// niit迎新脚本

// 身份证号数组
var idArray = [
    "320321200311050224",
    "320826200307161289",
    "32038220031101105X",
    "620521200002036017",
    "320923200211225758",
    "320323200304160612",
    "321201199908281011",
    "340802200304190044",
    "410702200304253028",
    "320324200005290018",
    "320724200105282172",
    "321102200004281939",
    "320321200209060821",
    "610422200003040017",
    "320584200101194111",
    "321081200101023018",
    "372321200212208955",
    "320982200106284537",
    "320585200305300031",
    "321284200209296210",
    "320322200312194410",
    "14050220010827305X",
    "320382200307184513",
    "320223200009282419",
    "412728200004084213",
    "321302200001021657",
    "520402200004060100",
    "321081200212115716",
    "410823200306040138",
    "320902200110221051",
    "320321200306283613",
    "320305200304053320",
    "360481200210174059",
    "341222200103102419",
    "360103200205105432",
    "362331200204261319",
    "530423200211270614",
    "320803200502085010",
    "32128320060430005X",
    "320202200607244074",
    "320804200604080938",
    "320381200603172190",
    "321201200602235317",
    "320305200509111512",
    "321023200512220821",
    "321322200607045018",
    "320923200502201510",
    "321302200603264816",
    "320321200603253859",
    "321281200510202093",
    "321281200608062113",
    "320723200512134429",
    "321322200606206617",
    "341225200509244352",
    "320923200601248111",
    "320123200601214815",
    "320705200410096019",
    "320113200608161610",
    "320281200607033520",
    "320923200608061827",
    "320923200602280148",
    "320682200508145592",
    "360313200608150514",
    "320921200510282813",
    "321023200501011434",
    "320830200612011812",
    "32108820060121339X",
    "320923200601081817",
    "320684200607037178",
    "320321200512281432",
    "321011200608020618",
    "320402200601172218",
    "440802200505100416",
    "340321200602070851",
    "371322200602265416",
    "321302200608152813",
    "321084200603075212",
    "320722200501264547",
    "32100220051202241X",
    "320115200605180771",
    "321284200510225219",
    "320922200607263914",
    "320981200510144978",
    "320124200607142812",
    "320281200604130771",
    "320302200605211215",
    "320103200604211278",
    "520328200601197031",
    "320981200603033214",
    "320682200602211146",
    "320923200511063965",
    "321088200509217718",
    "320928200602084915",
    "32072220060730261X",
    "32038120050923851X",
    "32038120060217061X",
    "321023200511066017",
    "321324200511201875",
    "340823200512292557",
    "340207200603214017",
    "320382200603240217",
    "32132120060315443X",
    "320921200604070010",
    "320830200606071018",
    "320722200505256325",
    "321181200509167715",
    "610924200509084359",
    "321322200511155212",
    "320506200512112038",
    "321281200605080017",
    "320803200507202618",
    "341003200604232027",
    "341302200503068539",
    "320923200604130020",
    "321023200512125218",
    "320321200604223037",
    "320721200604186653",
    "321302200512227227",
    "320282200607074871",
    "320504200606071772",
    "32032320051109753X"
];

var inputElement = document.querySelector(".bh-advancedQuery-quick-search-wrap .bh-form-control");
var index = 0;

// 定义定时器函数
var intervalId = setInterval(function() {
    if (index >= idArray.length) {
        clearInterval(intervalId);
        alert("脚本已成功执行完毕！");
    } else {
        inputElement.value = idArray[index];
        index++;
    }
}, 5000);
