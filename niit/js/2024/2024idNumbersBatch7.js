// niit迎新脚本

// 身份证号数组
var idArray = [
    "530112200607140511",
    "450922200606252534",
    "320322200510238293",
    "320511200509274029",
    "511781200409103958",
    "320902200608296026",
    "321011200601050320",
    "320724200609060012",
    "421302200605166463",
    "32132220041125423X",
    "320382200602251045",
    "341823200601045815",
    "360122200605104219",
    "320830200606153216",
    "320324200603250913",
    "320323200602176057",
    "320282200503252573",
    "321281200606206494",
    "411329200604081917",
    "533124200509234221",
    "450304200508072534",
    "341522200601203374",
    "320722200606255444",
    "32068120051108013X",
    "220322200603230377",
    "510922200602096243",
    "321084200511054617",
    "32132420050124429X",
    "320506200607081019",
    "32108820060101434X",
    "32012420051010262X",
    "320682200602259289",
    "320382200507137017",
    "341522200509022562",
    "320682200605122309",
    "34150320060428254X",
    "320684200510285415",
    "320681200604010034",
    "320583200510220417",
    "32020620060601001X",
    "320681200509230821",
    "321281200509147154",
    "321182200603120055",
    "341424200606172666",
    "320581200507141918",
    "320682200511079274",
    "32068420051129003X",
    "320681200608140821",
    "320681200608240048",
    "320684200603295452",
    "320581200604063210",
    "320682200510255782",
    "320981200604072223",
    "320301200504024043",
    "320124200409270629",
    "320681200608110016",
    "32092120060413503X",
    "320982200609096714",
    "320684200608300871",
    "320981200604173462",
    "320923200308036339",
    "320923200212212430",
    "320125200211290719",
    "32112120001228141X",
    "321324200202155473",
    "371002200112080521",
    "320321200305181826",
    "320381200207046037",
    "371324200208204714",
    "130125200306300021",
    "412726199809140819",
    "320724200303206023",
    "360421200107174011",
    "320323200301142224",
    "320581200308290339",
    "320722200304184513",
    "320721200210081816",
    "321023200303060454",
    "411527200211200514",
    "341282200301237310",
    "140724200101190031",
    "320322200309050811",
    "341502200309247630",
    "320722199912040059",
    "320121200211203317",
    "321088200308190328",
    "320683200309143152",
    "331024200310095565",
    "321322200402044257",
    "331004200308131226",
    "331081200303160012",
    "320981200003166478",
    "341324200307097824",
    "32030119991129001X",
    "32028220030707487X",
    "320322200301234229",
    "321324200210101272",
    "320922200306226829",
    "342423200112127763",
    "321283200211097433",
    "320922200302255753",
    "411525200308135170",
    "320681200203293619",
    "320923200305293911",
    "411326200208121113",
    "410724200305065078",
    "370304200302183917",
    "371428200204190010",
    "230121200212292014",
    "341502200211098171",
    "340322200109050044",
    "340621200209191215",
    "320723200310293253",
    "370283200202151213",
    "410102200307080094",
    "320830200207031422",
    "37021420020912301X",
    "411625200411035843",
    "320381200208310039",
    "321181200310011262",
    "371328200307181015",
    "320325200306029119",
    "321081200306055718",
    "340811200301085528",
    "320481200209103029",
    "34082220021119464X",
    "362524200403192018",
    "321321200206164626",
    "320123200303253410",
    "320585200003288014",
    "320581200304143825",
    "500383199905135759",
    "320981199705307217",
    "320982200307251010",
    "321088200210125755",
    "320981200311230232",
    "320324200111032311",
    "321084200210051922",
    "320722200305065452",
    "411481200312282419",
    "320282200109113831",
    "320982200104110875",
    "320105200306143433",
    "321283200306156029",
    "321282200105222214",
    "320722200509164516",
    "320981200510297229",
    "321324200605130211",
    "32038120060309492X",
    "321322200512074617",
    "321281200602243378",
    "320803200603263613",
    "331024200607052531",
    "321322200601194813",
    "34162120060412171X",
    "532926200509231739",
    "320321200607264838",
    "500235200512170675",
    "451421200604285532",
    "320501200602287251",
    "370832200608234759",
    "320724200412184521",
    "320721200602180098",
    "320123200604051417",
    "350524200608041033",
    "32080320060821321X",
    "41042320050718905X",
    "140321200601160073",
    "320206200511091232",
    "340323200609286973",
    "320723200604244211",
    "320682200509192198",
    "320829200510291213",
    "320981200603074470",
    "32102320060209681X",
    "320321200606172413",
    "320623200608115278",
    "522229200407031620",
    "321283200608300436",
    "520330200607071490",
    "44122320051004261X",
    "341204200604272616",
    "320381200601142131",
    "320301200511281697",
    "320411200605126135",
    "510722200704220291",
    "421002200603161814",
    "321003200608296614",
    "32132420051204041X",
    "320321200602024237",
    "32072220050119733X",
    "450521200507250017",
    "321322200604112430",
    "320721200601270075",
    "141023200604290209",
    "320322200605082834",
    "320381200511182172",
    "621026200704012517",
    "510502200509250114",
    "320282200604092564",
    "361026200511080034",
    "320504200608073018",
    "320402200602035813",
    "321323200605010619",
    "321322200608214450",
    "32050420060413176X",
    "320826200605012820",
    "350583200606219213",
    "321321200603241613",
    "320682200512103256",
    "320721200603110032",
    "411503200603140636",
    "320125200602171776",
    "320115200509264115",
    "522101200403127615",
    "532901200512264614",
    "320583200603029611",
    "511902200608023016",
    "331125200606043619",
    "320922200512093617",
    "340421200403024057",
    "32032220041023341X",
    "321284200510264437",
    "320113200604015616",
    "320811200409221057",
    "320826200509062617",
    "361102200510285016",
    "320621200501207516",
    "341422200505014818",
    "320681200604135830",
    "320681200606200042",
    "320623200607037810",
    "341302200408273622",
    "340803200602182014",
    "410327200609100263",
    "341225200401255113",
    "320321200412163615",
    "320684200602206673",
    "320582200512061716",
    "320981200605205462",
    "32068320061013081X",
    "320205200608173211",
    "320682200512243259",
    "321323200512182112",
    "320682200607299271",
    "320682200608014696",
    "320321200503180445",
    "320301200511224019",
    "341182200604072618",
    "320206200602122452",
    "320684200604203927",
    "321323200505210429",
    "320115200508272113",
    "320324200508142527",
    "320682200604033603",
    "320682200401025590",
    "371324200508065218",
    "320684200506060272",
    "320682200606191744",
    "421181200604088234",
    "321202200602030916",
    "320682200608301732",
    "321322200603015014",
    "320623200511165287",
    "341202200206082515",
    "32132120000606441X",
    "320722200002117710",
    "513902200001107178",
    "321088200308044136",
    "320922200205220815",
    "320382200111285531",
    "320584200104110059",
    "140431200306218832",
    "411424199912021674",
    "320302200212090010",
    "340811200206216518",
    "321323200305184510",
    "341322200308023218",
    "340821199912292718",
    "320382200301185929",
    "320602200308214520",
    "320324200009152114",
    "131002200010284617",
    "320830200101141236",
    "371327199911145414",
    "320722200107223616",
    "320311200301025823",
    "320928200104174918",
    "320321200107243010",
    "320322200008301613",
    "321322200110141813",
    "320586200005214517",
    "320924200202154819",
    "622225200005181819",
    "321183200211105514",
    "332502200308103984",
    "320583200010143312",
    "320382200008021319",
    "321283200005096819",
    "330382200307044010",
    "370403200307151841",
    "320826200009171235",
    "320481200101206819",
    "320322200207170812",
    "320324200007160612",
    "41052119981201451X",
    "370103200211168845",
    "320583200211145517",
    "41032520021026006X",
    "411527200209261027",
    "340621200209119344",
    "412723199901059010",
    "622727200009213515",
    "320322200001088231",
    "320324200008125931",
    "342201199812127911",
    "320381200305216335",
    "320928200007118631",
    "650203200212011818",
    "320381200010023835",
    "321323200101040232",
    "320722200211022339",
    "32072420000111481X",
    "320882200103061236",
    "411222200102212032",
    "320586200003214812",
    "612501200106027312",
    "320721200209106019",
    "411525200301265159",
    "321088200010311035",
    "32128420010723561X",
    "320722200005047316",
    "320826200012132616",
    "320924200106302930",
    "320721200003162613",
    "32011220010529081X",
    "320324200304065662",
    "320322200012066230",
    "321322200312147412",
    "411302200307240133",
    "43070320021015677X",
    "320582200106137631",
    "412826200305203514",
    "32132220010401405X",
    "360428200203052940",
    "320382200301220253",
    "330501200212177817",
    "341424200210284119",
    "321003200212094821",
    "320581200304163412",
    "320382200011180417",
    "320102200404024615",
    "320201200407223010",
    "320922200107204715",
    "320681200405200214",
    "320102200408274611",
    "320924200408100875",
    "320282200403212574",
    "341324200409241014",
    "320106200406080851",
    "320103200403090772",
    "341224200310207814",
    "342423200310013599",
    "340321200307095300",
    "411522200409043065",
    "320830199909261411",
    "320106200401091228",
    "320107200408123436",
    "320723200408044431",
    "320682200109284077",
    "321324200401205496",
    "321283200205153611",
    "37172320040801032X",
    "41072420040513101X",
    "320621200211216537",
    "320381200408054914",
    "341322200311284021",
    "511011200212246771",
    "320324200311131613",
    "320382200510135215",
    "320282200408312419",
    "320324200401235416",
    "321182200206291715",
    "371728200506165167",
    "320102200103193810",
    "360121200412190038",
    "320382200402267835",
    "320104200407161618",
    "411525200408248471",
    "320925200203135114",
    "360921200404090911",
    "320107200312085025",
    "321023200408110104",
    "320106200312072017",
    "142731200106306318",
    "320981200206196714",
    "340881200106182639",
    "320581200405010319",
    "320106200406270831",
    "320121200406243546",
    "642221200206164095",
    "320582200203094549",
    "340421200210285832",
    "321283200211092413",
    "321284200311171625",
    "320724200208201224",
    "320323200306246030",
    "321282200309192627",
    "522401200303161230",
    "320981200205135717",
    "321284200301125617",
    "320323200208034210",
    "320924200303182915",
    "320103200305212035",
    "320928200308251516",
    "411528200205102914",
    "32130120030124002X",
    "320323200305222619",
    "320481200303224644",
    "320105200306213411",
    "34088120030630221X",
    "321322200204023439",
    "341424200304084580",
    "321324200302255244",
    "32012320020527121X",
    "32072220030710307X",
    "320705200210134527",
    "522422200208219889",
    "321081200212151514",
    "320281200301316528",
    "321023200301055213",
    "320322200206301323",
    "320382200302201628",
    "321324200305090043",
    "320321200211090413",
    "341282200202124628",
    "320705200309263513",
    "320922200307251436",
    "411525200301054570",
    "500223200603161437",
    "320724200511133615",
    "32072120060814301X",
    "320124200601032620",
    "321324200502204433",
    "36242120050807171X",
    "411524200411171536",
    "320281200604071038",
    "620622200607192014",
    "421127200604223274",
    "320724200509034810",
    "530302200511271216",
    "321181200603283165",
    "520326200512130019",
    "321323200608301243",
    "321201200601030010",
    "320923200602050916",
    "450203200607140721",
    "320382200602112571",
    "320804200605024719",
    "140109200504220059",
    "321322200703277011",
    "52222920050628982X",
    "320582200606143915",
    "320682200512230028",
    "53322220051015159X",
    "320982200602061017",
    "522201200605280154",
    "320921200512263333",
    "320924200608213428",
    "512081200507138735",
    "320124200605313614",
    "320684200608293915",
    "321322200511051034",
    "320381200608207962",
    "32062320060130042X",
    "520201200704100049",
    "350305200607312014",
    "211002200412284812",
    "341422200602192712",
    "52038220041119003X",
    "320105200605301622",
    "32068220050101627X",
    "320682200602264088",
    "320303200512224312",
    "522322200511052051",
    "520323200704025011",
    "360981200612245072",
    "411425200609302117",
    "32072120051013262X",
    "320506200509052089",
    "320112200601131623",
    "321322200607211434",
    "321181200602200428",
    "320583200506283837",
    "32011520060319431X",
    "360502200703091331",
    "140882200608160279",
    "522323200609288544",
    "320826200509214617",
    "321323200601100211"
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
