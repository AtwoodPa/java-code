// niit迎新脚本

// 身份证号数组
var idArray =
    [];

var inputElement = document.querySelector(".bh-advancedQuery-quick-search-wrap .bh-form-control");

var index = 0;

// 定义定时器函数
var intervalId = setInterval(function() {
    // 检查索引是否超出数组长度
    if (index >= idArray.length) {
        // 停止定时器
        clearInterval(intervalId);
    } else {
        // 填入身份证号
        inputElement.value = idArray[index];
        // 递增索引
        index++;
    }
}, 5000); // 每五秒执行一次
