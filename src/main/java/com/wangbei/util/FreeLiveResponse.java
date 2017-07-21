package com.wangbei.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyidi 2017-07-21 18:17:25
 * @class com.wangbei.util.FreeLiveResponse
 * @description 放生响应结果
 */
public class FreeLiveResponse {
    public static final List<String> freelive = new ArrayList<>();

    static {
        freelive.add("愿以此放生功德庄严佛净土、上报四重恩、下济三途苦、若有见闻者、悉发菩提心、尽此一报身、同生极乐国！ (回向功德)");
        freelive.add("放生功德殊胜行，无边胜福皆回向。");
        freelive.add("普愿沉溺诸众生，速往无量光佛刹。");
        freelive.add("十方三世一切佛，一切菩萨摩诃萨，摩诃般若波罗蜜！南无阿弥陀佛！（回向文）");
        freelive.add("(合掌）弟子——愿以此——之功德，佛力加持真诚回向给弟子——累生累世的冤亲债主历代宗亲,所堕胎儿孩子和从小至今所伤害的一切含灵蜎(yuan)飞碝（ruan)" +
                "动大菩萨。祈请佛菩萨菩萨慈悲作主，威神超拔他们，令他们当下消除一切的业障，释仇解怨，放下与世间人的怨恨，离苦得乐往生净土！弟子——真心求忏悔。（三遍）");
    }

    public void addFreeLiveResponse(String response) {
        freelive.add(response);
    }

    public static String random() {
        return freelive.get(RandomUtil.getRandomInt(freelive.size()));
    }
}
