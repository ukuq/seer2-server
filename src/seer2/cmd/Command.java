package seer2.cmd;

import java.util.HashMap;

public enum Command {
    GET_VERIFY_CODE_101(101, "验证码"),
    LOGIN_103(103, "Login"),
    ACTIVE_CODE_104(104, "Set active code"),
    GET_RECOMMEND_SERVER_LIST_105(105, "Get recommend server list"),
    GET_RANGED_SERVER_LIST_106(106, "Get ranged server list"),
    CREATE_ROLE_107(107, "创建角色"),
    CHECK_CREATED_ROLE_110(110, "Check has role"),
    CHECK_HAS_ROLE_111(111, "Check role exists in data base"),
    DEPUTIZE_SEND_999(999, "发送代理地址给online服务器"),
    ONLINE_LOGIN_1001(1001, "Login to online server"),
    SYNC_SERVER_TIME_1100(1100, "Synchronize time of server"),
    SYNC_POSITION_1101(1101, "Synchronize postion of actors"),
    LIST_USER_1004(1004, "地图---玩家列表"),
    USER_ENTER_MAP_1002(1002, "地图---玩家进入"),
    USER_LEAVE_MAP_1003(1003, "地图---玩家离开"),
    USER_CHANGE_NICK_1099(1099, "改变昵称"),
    USER_CHANGE_MEDAL_1008(1008, "User change medal"),
    USER_CHANGE_SIGNATURE_1029(1029, "User change signature"),
    USER_GET_SIMPLE_INFO_1028(1028, "获取简单用户信息"),
    USER_GET_DETAIL_INFO_1010(1010, "获取详细用户信息"),
    ITEM_GET_LIST_1005(1005, "Get item list"),
    ITEM_REMOVE_1009(1009, "Remove item"),
    ITEM_ADD_1006(1006, "Add item or equip"),
    EQUIP_GET_LIST_1007(1007, "获取装备列表"),
    EQUIP_CHANGE_1098(1098, "改变装备"),
    SERVER_CLOSE_NOTIFY_1012(1012, "服务器关闭通知"),
    QUEST_ACCEPT_1011(1011, "接受任务"),
    QUEST_ABORT_1013(1013, "取消任务"),
    QUEST_GET_BUFFER_1014(1014, "获取任务信息"),
    QUEST_SUBMIT_STEP_BUFFER_1015(1015, "设置任务步骤信息"),
    FIGHT_CATCH_PET_1031(1031, "捕捉精灵"),
    FIGHT_USE_MEDICINE_1048(1048, "使用道具"),
    FIGHT_START_WILD_1500(1500, "对战野怪"),
    FIGHT_RES_READY_1501(1501, "用户资源加载完成"),
    FIGHT_USE_SKILL_1502(1502, "用户选择技能"),
    FIGHE_PLAYER_INVITE_1503(1503, "邀请用户对战"),
    FIGHE_PLAYER_ACCEPT_1504(1504, "接受用户对战"),
    FIGHE_PLAYER_CANCEL_1505(1505, "取消邀请"),
    FIGHT_END_1507(1507, "战斗结束"),
    FIGHT_INVITE_1508(1508, "online向用户发送战斗请求包"),
    FIGHT_ESCAPE_1509(1509, "用户逃离战斗"),
    FIGHT_INVITE_PVP_1512(1512, "PVP邀请"),
    FIGHT_ACCEPT_PVP_1513(1513, "PVP接受"),
    FIGHT_CANCEL_INVITE_PVP_1514(1514, "PVP取消邀请"),
    ACCEPT_FIGHT_PVP_1515(1515, "通知用户,被邀请方接受"),
    FIGHT_LOAD_RES_1(1, "拉取战队双方信息 （1~1000为btl直接传给cli，无需online解包、打包 ）"),
    FIGHT_TURN_START_2(2, "战斗会合开始"),
    FIGHT_HURT_NOTIFY_3(3, "在一回合中，通知计算结果的通知包 （无请求包，返回包为通知包）"),
    FIGHT_LOAD_MAP_4(4, "对战双方已应答，通知客户端可以加载对战资源 （无请求包，返回包为通知包）"),
    FIGHT_GET_REVENUE_5(5, "战斗结束，获胜方获得经验通知 （无请求包，返回包为通知包）"),
    FIGHT_NEXT_TURN_7(7, "通知一回合开始 （无请求包，返回包为通知包）"),
    FIGHT_CHANGED_NOTIFY_8(8, "通知用户交换了精灵 （无请求包，返回包为通知包）"),
    FIGHT_BUFF_RESULT_NOTIFY_9(9, "通知每回合状态影响 （无请求包，返回包为通知包）"),
    FIGHT_USE_ITEM_NOTIFY_10(10, "使用道具后的广播包"),
    FIGHT_FEATRUE_RESULT_11(11, "精灵特性改变精灵数据通知包"),
    FIGHT_ESCAPE_NOTIFY_12(12, "Fight escape notify"),
    FIGHT_NOTIFY_MON_POS_15(15, "通知位置上更新精灵信息"),
    FIGHT_UPDATE_ANGER_16(16, "通知位置上更新双方怒气值"),
    PVP_FIGHT_NOTIFY_MON_POS_17(17, "PVP_通知位置上更新精灵信息"),
    FIT_CHANGE_HP_POS_18(18, "合体变身通知血量"),
    FIGHT_CHANGE_PET_19(19, "变身精灵"),
    FIGHT_CHANGE_FIGHTER_1032(1032, "Fight change fighter"),
    CHAT_1102(1102, "Chat"),
    FIGHT_RING_INFO_1104(1104, "擂台信息"),
    CLIENT_BROADCAST_DATA_1105(1105, "客户端广播数据"),
    HOME_GET_BUDDY_NEWS_1041(1041, "Get buddy news in home scene"),
    HOME_GET_HONOR_INFO_1052(1052, "Get honor in home scene"),
    HOME_GET_ENTRY_INFO_1038(1038, "Get enter info in home scene"),
    HOME_GARBAGE_CLEAN_1123(1123, "小屋---清理垃圾"),
    GET_DREAM_REWAED_1053(1053, "Get reward after dream"),
    PET_SPAWN_1103(1103, "Pet spawn in lobby scene"),
    PET_CURE_1037(1037, "用赛尔豆增加血量"),
    PET_TRAINING_START_1039(1039, "设置训练精灵"),
    PET_TRAINING_FINISH_1040(1040, "Finish training pet in home scene"),
    PET_TRAINING_FIGHT_1042(1042, "Fight training pet in home scene"),
    PET_REPLACE_SKILL_1030(1030, "Pet Skill Replaced"),
    PET_ADD_LEARNING_POINT_1033(1033, "Pet learning point added"),
    PET_GET_DICTIONARY_LIST_1034(1034, "Get pet list of dictionary"),
    PET_REWARD_STATUS_1058(1058, "Get petDictionaryRewardStatus"),
    GET_REWARDS_HANDBOOK_1036(1036, "Get petDictionary Collect Rewards"),
    PET_GET_STORAGE_LIST_1016(1016, "获得仓库的精灵列表"),
    PET_SIMPLE_INFO_1017(1017, "Get pet simple infomation"),
    PET_SET_STARTING_1018(1018, "设置对战首发精灵"),
    PET_SET_FOLLOWING_1019(1019, "设置精灵跟随与否"),
    PET_SET_STORAGE_STATUS_1020(1020, "设置精灵放仓库或放背包里：当精灵从仓库放入背包时，若背包满，服务器发错误码，客户端接收到错误码后，请用户在背包中选择一只替换精灵，客户端发1020（flag=0），将替换精灵放至仓库，再发1020（flag=1），将原精灵放入背包"),
    PET_SET_FREE_STATUS_1021(1021, "Set pet free status"),
    PET_GET_FREE_LIST_1022(1022, "获得放生仓库精灵列表"),
    PET_STORAGE_TREASURE_1130(1130, "精灵仓库---寻宝"),
    PET_WASH_LEARNING_POINT_1159(1159, "精灵---使用物品洗学习力"),
    CHECK_ONLINE_1023(1023, "Check online buddy"),
    BUDDY_ADD_1024(1024, "Add Buddy Or Black"),
    BUDDY_REMOVE_1025(1025, "Remove buddy"),
    BUDDY_NOTIFY_ADD_1026(1026, "Notify add buddy"),
    BUDDY_REPLY_ADD_1027(1027, "Reply add buddy"),
    MINI_GAME_START_1043(1043, "开始小游戏"),
    MINI_GAME_OVER_1044(1044, "离开小游戏"),
    BOSS_START_FIGHT_1046(1046, "Boss start fight"),
    ITEM_SERVER_GIVE_1051(1051, "Item sever active give"),
    ITEM_EXCHANGE_1055(1055, "Item exchange"),
    ITEM_ADD_EXPERIENCE_1056(1056, "使用加经验值的道具，精灵属性更改通知包"),
    ITEM_PET_UPDATE_1057(1057, "Item pet update"),
    SPT_GET_INFO_1059(1059, "获取SPT信息"),
    DIGGER_MINE_1060(1060, "挖矿"),
    CLIENT_GET_BUFFER_INFO_1062(1062, "向服务器请求客户端保存的数据"),
    CLIENT_SET_BUFFER_INFO_1063(1063, "向服务器设置客户端保存的数据"),
    TRAILS_DOOR_FIGHT_1064(1064, "向服务器请求试炼之门当前层的战斗精灵Id"),
    FIGHT_IN_TRAILSDOOR_1047(1047, "向服务器请求试炼之门开始战斗"),
    DOOR_OUT_1049(1049, "退出门对战"),
    DAILY_LIMIT_1065(1065, "获取每日上限的剩余次数"),
    ACTIVITY_FIGHT_MONSTER_1066(1066, "fight monster in activity"),
    ADD_ALLPETBAT_BLOOD_1069(1069, "add all petbag pets\' blood"),
    CHECK_DAILY_REWARD_1061(1061, "Check Daily Reward"),
    FISH_FIGHT_1165(1165, "与钓上的精灵对战"),
    MAGIC_NUM_QUERY_1067(1067, "神奇密码查询"),
    MAGIC_NUM_USE_1068(1068, "神奇密码使用"),
    BEAT_CAPTAIN_1070(1070, "BeatCaptain"),
    VOTE_SEND_INFO_1071(1071, "Send Vote Info"),
    VOTE_GET_INFO_1072(1072, "Get Vote Info"),
    NOTIFY_SYS_MSG_1073(1073, "详细通告"),
    GET_PETINFO_1074(1074, "获取精灵信息"),
    SHOOT_1075(1075, "射击"),
    SET_THIS_SUMMONED_1076(1076, "设置自己为召集者"),
    SET_SUMMONED_ID_1077(1077, "设置自己的召集者的ID"),
    GET_SUMMONED_INFO_1078(1078, "获取被召集者列表"),
    DOOR_FIGHT_MATCH_1079(1079, "请求门战匹配"),
    DOOR_FIGHT_MATCH_CANCEL_1080(1080, "取消门战匹配"),
    DOOR_FIGHT_MATCH_END_1082(1082, "门战匹配结果"),
    DOOR_FIGHT_WAR_PET_INFO_13(13, "门战对方选择的精灵"),
    TEAM_PREPARE_ACTIVITY_1081(1081, "提交意见表"),
    TEAM_CREATE_1083(1083, "战队---创建"),
    TEAM_INVITE_JOIN_1084(1084, "战队---邀请加入"),
    TEAM_INVITE_NOTIFY_1085(1085, "战队---邀请通知"),
    TEAM_INVITE_REPLY_1086(1086, "战队---邀请回复"),
    TEAM_MEMBER_LIST_1089(1089, "战队---拉取列表"),
    TEAM_MEMBER_QUERY_1090(1090, "战队---成员查询"),
    TEAM_MEMBER_KICK_1092(1092, "战队---踢出成员"),
    TEAM_QUIT_1093(1093, "战队---退出战队"),
    TEAM_APPLY_JOIN_1166(1166, "战队---申请加入"),
    TEAM_APPOINT_1567(1567, "战队---任命为副队长"),
    TEAM_CANCEL_1568(1568, "战队---撤销副队长"),
    TEAM_Reject_1571(1571, "战队---拒绝加入战队"),
    MINIGAME_MOREGAIN_1091(1091, "额外物品奖励的游戏id集"),
    INITPET_TRAINING_STEP_COMPLETE_1087(1087, "Init Pet Training Step Complete"),
    INITPET_TRAINING_STEP_STATUS_1088(1088, "Get Init Pet Training Step Status"),
    SELECT_ACTIVITY_TROOP_1106(1106, "选择活动队伍"),
    GET_ACTIVITY_TROOP_FRACTION_1108(1108, "获取每队的分数"),
    NOTICE_PVP_COPE_1107(1107, "通知PVP对战"),
    CANCEL_NOTICE_1109(1109, "通知方取消邀请"),
    ACTIVITY_ALL_USER_STATUS_1110(1110, "返回活动中所有用户的状态"),
    HONOR_EXCHANGE_1097(1097, "荣誉兑换"),
    START_FIGHT_SERVER_VERIFY_1111(1111, "Server require verifying for start fight wild or SPT boss"),
    START_FIGHT_CLIENT_VERIFY_1112(1112, "Client do verifying for start fight wild or SPT boss"),
    CLIENT_SET_SVR_BUF_INFO_1113(1113, "Client set server buffer info"),
    ITEM_APPRISAL_1116(1116, "洗资质"),
    BRANCH_EVOLUTION_1117(1117, "分支进化"),
    FIGHT_NPC_1511(1511, "和NPC战斗"),
    TEAM_DISSOLVE_1118(1118, "dissolve team"),
    TEAM_TRANSFERCAPTAIN_1119(1119, "transfer team captain"),
    TEAM_MODIFYINFO_1120(1120, "modify team info"),
    TEAM_NEWS_1121(1121, "拉取战队新闻的信息"),
    GET_ONLINE_CAN_SWAP_NUM_1122(1122, "查询在线送奖励的可送个数"),
    HALLOWEEN_GET_USER_NICK_1125(1125, "查询万圣节小屋好友对战信息"),
    GET_GIFT_CANGET_1124(1124, "查询精灵图鉴礼包是否可领取"),
    GET_GAME_CANPLAY_1126(1126, "查询游戏是否可玩"),
    TEAM_INFO_1127(1127, "获得军团签到信息、挑战信息、公告等"),
    TEAM_REGISTER_1128(1128, "军团签到"),
    TEAM_SAVENOTICE_1129(1129, "更新军团公告"),
    TEAM_SAVEMSG_1570(1570, "更新入队须知"),
    TEAM_GET_UPGRADE_INFO_1135(1135, "获取战队升级信息"),
    TEAM_UPGRADE_1136(1136, "战队升级"),
    TEAM_SAVE_COINS_1138(1138, "给战队存钱"),
    TEAM_RANK_ID_1148(1148, "TOP100战队ID"),
    TEAM_RANK_SIMPLEINFO_1149(1149, "战队排名简单信息"),
    BAIT_FISH_1132(1132, "放饵钓鱼"),
    GET_FISH_1133(1133, "拉竿,获取鱼"),
    SHOW_FISH_LIST_1137(1137, "展示钓过的鱼"),
    GET_VIP_TRY_1134(1134, "vip预热活动"),
    ACTIVITY_PRE_FISH_1139(1139, "活动--钓鱼预热"),
    RANDOM_EVENT_1140(1140, "随机事件或物品"),
    FRIENDSHIP_TREE_REAP_1143(1143, "收获友谊果实"),
    COOKING_CLICK_1144(1144, "烹饪"),
    CHANGE_COLOR_1145(1145, "改变赛尔颜色"),
    ACTIVE_COUNT_1142(1142, "活动次数"),
    DOUBLEEXPTIME_INFO_1153(1153, "获取用户累积的双倍经验时间信息"),
    DOUBLEEXPTIME_DRAW_1157(1157, "领取双倍经验时间"),
    VIP_GAME_START_1146(1146, "vip游戏开始"),
    VIP_GAME_OVER_1147(1147, "vip游戏结束"),
    VIP_UPDATE(1152, "vip信息更新"),
    SESSION_1156(1156, "获取Session"),
    VIP_PAY_NOTICE(1160, "vip付费通知"),
    FROZEN_ACTIVITY_TYPE_1151(1151, "冰封活动广播报类型"),
    FROZEN_OUT_OR_OVER_1150(1150, "用户离开或者进入5个点"),
    FROZEN_FIGHT_1154(1154, "头部射击打中冰块或者人"),
    FROZEN_DEFEND_1155(1155, "用户在盾里"),
    FROZEN_STATUS_1158(1158, "轴的血和状态"),
    GET_DEVIL_TRAIN_1163(1163, "获取魔鬼训练精灵列表"),
    DEVIL_TRAIN_1164(1164, "魔鬼训练开始或者结束"),
    PET_PYRAMID_CHALLENGE_QUERY_1161(1161, "精灵金字塔挑战查询"),
    PET_PYRAMID_CHALLENGE_RANK_1162(1162, "精灵金字塔挑战排行"),
    VIP_LUCKY_MSG_1168(1168, "vip幸运礼包"),
    HANI_SNOW_QUERY_1167(1167, "哈尼雪人活动信息查询"),
    CHANGE_CHARECTER_1169(1169, "洗性格"),
    LUCKY_TURN_TABLE_1170(1170, "幸运转盘开始，扣除游戏币"),
    LUCKY_TURN_TABLE_1171(1171, "幸运转盘结束，领奖励"),
    PLAYING_DEATH_LIST_1172(1172, "获取玩法中死亡的精灵"),
    GET_CURRENT_POKE_COUNT_1173(1173, "获得当前阶段遇到扑克怪的数量"),
    CHRISTMAS_MAKE_1174(1174, "ChristmasMake"),
    INVITE_FRIEND_1175(1175, "12月16日以后注册的用户填写老用户的米米号"),
    INVITE_FRIEND_1176(1176, "填写此用户为召集用户的米米号的数目"),
    GET_VIP_GIFT_1177(1177, "获取vip充值礼包个数"),
    GET_SPECIAL_ITEMINFO_1178(1178, "获取特殊物品信息"),
    CHRISTMAS_WISH_1179(1179, "圣诞许愿"),
    CALL_FIRE_DRAGON_1180(1180, "召唤火龙"),
    SEND_SOCKS_GIFT_1181(1181, "给好友送圣诞袜礼物"),
    GET_SOCKS_REWARD_1182(1182, "领取圣诞袜礼物"),
    GET_SOCKS_USER_LIST_1184(1184, "获取圣诞袜礼物用户id和nick列表"),
    SERVER_TYPE_1183(1183, "用户从哪个网络代签过来"),
    REPLACE_PET_1185(1185, "广播替换精灵"),
    GET_PUBLIC_ACTIVITY_COUNT_1186(1186, "获取公共活动次数"),
    PUBLIC_ACTIVITY_1187(1187, "公共活动"),
    DECORATE_CHEER_1188(1188, "新年新气象加油"),
    TRAINER_TASKS_STATUS_1189(1189, "获取训练师成长任务状态"),
    TRAINER_TASK_REWARD_1190(1190, "训练师成长任务完成领奖"),
    SELECT_FIGHT_MONS_1192(1192, "用户选择战斗的精灵"),
    SET_SECOND_MON_1193(1193, "设置对战辅位精灵"),
    SET_NONO_FLAG_1194(1194, "设置NONO跟随(返回包为广播包)"),
    GET_PET_PRACTICE_INFO_1195(1195, "getPetPracticeInfo"),
    SET_PET_PRACTICE_INFO_1196(1196, "setPetPracticeInfo"),
    NONO_TIME_BUTLER_UPDATE_1197(1197, "NONO时间管家广播包"),
    BAB_LANGUAGE_1198(1198, "脏词显示"),
    GET_VIPREWARD_INFO_1199(1199, "获取VIP奖励信息"),
    ITEM_MAX_NOTIFY_INFO_1200(1200, "物品最大值通知包"),
    MAP_RATE_PET_INFO_1201(1201, "地图稀有精灵通知包"),
    MEE_COIN_INFO_1202(1202, "米币账户余额"),
    MEE_COIN_RECRAGE_1203(1203, "米币充值"),
    GET_BIRTH_SYSTEM_INFO_1204(1204, "获取繁殖精灵信息"),
    START_BIRTH_PET_1205(1205, "开始繁殖精灵"),
    CLOSE_BIRTH_1206(1206, "取消繁殖精灵"),
    SET_BIRTH_HOME_1207(1207, "设置小屋精灵"),
    GET_TWO_PET_1208(1208, "获取二代精灵"),
    GET_RANGE_DAY_LIMIT_1209(1209, "得到某个范围内用户每天做的事情的数目"),
    GET_DAY_RANDOM_PET_1210(1210, "获取后台每日随机精灵"),
    GET_IS_START_ONHOOK_1212(1212, "获取挂机跟随精灵获得的总经验值"),
    GET_DAY_EXP_TIME_1213(1213, "获取今天双倍时间和学习力的倍数"),
    SET_BUDDY_POINT_1214(1214, "赠送给好友点"),
    TREAT_ALL_PET_1215(1215, "群体医疗"),
    PET_INFO_MESSAGE_1216(1216, "通知精灵相关信息（后台主动发的）"),
    SELECT_PET_FIGHT_1217(1217, "选择群体精灵对战"),
    SYSTEM_PET_FIGHT_1218(1218, "用系统的精灵对战"),
    GET_TOTAL_VOTE_INFO_1219(1219, "获取所有票数信息"),
    GET_DAYLIMIT_INFO_1220(1220, "得到日常限制信息"),
    GET_PET_KING_AWARD_1221(1221, "获取精灵王抢分赛奖励"),
    GET_SHOP_INFO_1223(1223, "获取商品详细信息"),
    MI_BUY_ITEM_1224(1224, "用米币购买商品"),
    CHECK_MI_CIPHER_1225(1225, "检查密码是否正确"),
    CHECK_SETUP_CIPHER_1226(1226, "是否设置过支付密码"),
    GET_TEAM_BLOOD_1227(1227, "当前血量"),
    NOT_1070(1228, "模仿1070"),
    GET_WOHOO_PVP_INFO_1229(1229, "获取单挑王PVP信息"),
    GET_INTEGRAL_INFO_1230(1230, "获取积分信息"),
    GET_CURR_BLOOD_1231(1231, "当前血量"),
    GET_RANKING_LIST_1232(1232, "获取排名序列"),
    GET_HOPES_LIST_1233(1233, "提取用户愿景"),
    GET_PET_ITEM_INFO_1234(1234, "获取精灵药品信息"),
    UPDATE_PET_ITEM_INFO_1235(1235, "更新精灵药品信息"),
    ELEMENT_EQUIP_1236(1236, "装备附魔"),
    MI_EXCHANGE_ITEM_1239(1239, "米币兑换物品"),
    CLI_NOTIFY_DEL_MONSTER_1259(1259, "删除精灵"),
    GRASP_SKILL_1246(1246, "一键习得"),
    BATTERY_SWITCH_1516(1516, "电池开关"),
    GET_INVITE_INFO_1517(1517, "邀请好友信息"),
    GET_PLANT_INFO_1518(1518, "获取田的信息"),
    PLANT_SET_INFO_1519(1519, "田块操作信息"),
    CLI_SUBMIT_REPORT_ADD_1520(1520, "获取装备特殊属性"),
    GET_RANK_LIST_1521(1521, "拉取总排名"),
    GET_ACTOR_RANK_1522(1522, "获取自己的排名"),
    PET_CHARA_PRACTICE_1525(1525, "精灵性格修炼"),
    UPDATE_PET_CHARA_INFO_1526(1526, "更新精灵性格相关属性"),
    SUPER_PET_PRACTICE_1528(1528, "精灵一键搞定"),
    CLI_MIBI_GET_GIFT_INFO_1529(1529, "用户米币消费获取礼物次数"),
    SERVER_MESSAGE_8000(8000, "Server message"),
    CLI_SET_WIN_COPY_1237(1237, "完成某个副本关卡"),
    CLI_GET_WIN_COPY_1238(1238, "拉取所有完成的副本关卡信息"),
    SET_CHPHER_IS_OPEN_1240(1240, "设置支付密码的开关"),
    DAY_LIMIT_LIST_1241(1241, "dayLimit序列拉取"),
    CLI_RAND_MONSTER_INDEX_1242(1242, "暴食回廊中客户端随机精灵信息通知服务器"),
    CLI_NOTI_COPY_MAP_FIN_1243(1243, "通知副本完成(由后台主动通知，不需要请求包)"),
    CLI_CHECK_HAVE_THIS_NUM_ID_1244(1244, "判断用户是否拥有此类的精灵"),
    MYSTERY_YITE_MAP(1245, "神秘伊特中获取地图的跳转顺序"),
    CLI_ASK_TO_DRESS_UP_1247(1247, "玩家申请变身"),
    CLI_ASK_TO_ENTER_CHUANGO_1248(1248, "玩家请求串串锅的状态"),
    CLI_ASK_CHUANGO_INFO_1249(1249, "串串锅的状态信息"),
    CLI_RAND_SOME_INDEX_1250(1250, "获取随机的下标,凯伊对决"),
    CLI_FIGHT_MON_SELECT_MON_1251(1251, "选择出战精灵"),
    CLI_MONEY_COUNT_1253(1253, "查询星钻数量"),
    CLI_BUY_MONEY_COUNT_1254(1254, "米币购买星钻"),
    DELETE_MAIL_ON_SERVER(1252, "删除服务器邮件数据"),
    GET_MAIL_LIST_DATA(1255, "拉取邮件列表信息"),
    GET_SINGLE_MAIL_DATA(1256, "获取某一封邮件的具体数据"),
    GET_MAIL_AWARD_1257(1257, "获取邮件附件奖品"),
    NEW_MAIL_ARRIVE_1258(1258, "有新邮件"),
    ALL_SERVER_DATA_1527(1527, "全服数据"),
    CLI_GET_BOUGHT_LOTTERY_TICKET_INFO_1530(1530, "拉取购买了的获奖彩票号码和注数"),
    CLI_GET_LOTTERY_BINGO_INFO_1531(1531, "拉取历史中奖号码"),
    CLI_GET_LOTTERY_LUCKY_INFO_1532(1532, "拉取历史开奖号码"),
    GET_PET_INFO_1533(1533, "获取指定精灵信息"),
    INPUT_FRIEND_MIMI_1534(1534, "填写召回人的米米号"),
    GET_PET_NUMBERID_HAVE_1535(1535, "获取精灵种类ID有没有"),
    GET_VOTE_DATA_1536(1536, "获得吉祥三宝的18个选择数据（全服）"),
    CLI_GET_WORLD_BOSS_BLOOD_DIMUSEN_1537(1537, "蒂姆森之超能战纪，请求BOSS血量和阶段"),
    CLI_GET_WORLD_BOSS_BLOOD_DIMUSEN_1538(1538, "请求BOSS血量和阶段"),
    CLI_START_BREED_1539(1539, "精灵孵化器开始孵化"),
    CLI_SPEED_BREED_1540(1540, "孵化器孵化加速"),
    GET_HOME_FIGHT_COUNT_1191(1191, "获取小屋战斗次数"),
    CLI_GET_MON_FROM_BREEDING_1541(1541, "取下孵化精元或者领取孵化精灵"),
    CLI_GET_MON_FROM_BREEDING_1542(1542, "获取孵化器信息"),
    PINGTAI_GET_1543(1543, "平台触发一下"),
    PET_EVOLVE_1544(1544, "精灵魔神化"),
    CLI_GET_RANK_BEFORE_1545(1545, "拉取过往排行榜"),
    CLI_GET_MON_SIMPLE_INFO_IN_BAG_1546(1546, "拉背包指定精灵最新信息"),
    GET_CONIS_1547(1547, "主动送赛尔豆"),
    TEMP_NOTIFY_1548(1548, "后台通知有临时时间发生"),
    CLI_SET_MON_STATUS_VIP_1260(1260, "设置精灵放仓库或者VIP背包里"),
    CLI_EXCHANGE_MON_BETWEEN_BAG_AND_VIP_BAG_1261(1261, "增加普通背包和VIP背包的精灵互换协议"),
    CLI_ENTER_XINGHUNSHENGYU_1262(1262, "拉取星魂背包"),
    CLI_STARHUN_GET_STARHUN_1263(1263, "抽取星魂"),
    CLI_EXP_TO_COIN_STARHUN_1264(1264, "星力转换"),
    CLI_PUT_ALL_RUNES_STARHUN_1265(1265, "一键拾取星魂"),
    CLI_STAR_LEVEL_UP_1266(1266, "升级星魂"),
    CLI_STAR_LEVEL_UP_1267(1267, "移动星魂"),
    CLI_STAR_LEVEL_UP_1268(1268, "获得宠物星魂"),
    CLI_CALC_POTENTIAL_1269(1269, "资质洗练"),
    CLI_PASSWORD_1270(1270, "密保回答"),
    CLI_HAVECLIE_REQUEST_1271(1271, "一段时间内获取星钻数量"),
    GET_WISH_NUMS_1549(1549, "获取用户当天能够许愿的次数"),
    GET_TEAM_QUEST_1550(1550, "战队任务"),
    COM_TEAM_QUEST_1551(1551, "完成任务"),
    QUIT_TEAM_QUEST_1552(1552, "放弃任务"),
    NEW_TEAM_SHOP_BUY_1553(1553, "新战队购买"),
    TEAM_RANK_INFO_1554(1554, "新战队获取战队列表信息"),
    TEAM_BOSS_CURR_HP_1555(1555, "军团BOSS剩余血量"),
    TEAM_BOSS_CURR_GET_1556(1556, "军团BOSS奖励"),
    DREAM_ENTRY_MAP_1557(1557, "进入永恒梦境关卡"),
    DREAM_CURR_MAP_INFO_1558(1558, "永恒梦境当前关卡"),
    DREAM_AUTO_FIGHT_1559(1559, "永恒梦境自动挑战"),
    DREAM_FIGHT_PET_1560(1560, "永恒梦境挑战地图中的BOSS"),
    DREAM_FAMILY_INFO_1561(1561, "拉去梦境氏族列表"),
    DREAM_MAP_INFO_LIST_1562(1562, "永恒梦境氏族里的子列表"),
    DREAM_FAMILY_INFO_ONE_1563(1563, "永恒梦境单个氏族的信息"),
    DREAM_MAP_INFO_ONE_1564(1564, "永恒梦境单个氏族的信息"),
    CLI_PUBLIC_GET_KEY_VALUE_LIMIT_1273(1273, "获得指定的key范围内的value,数量限制在limit范围内"),
    CLI_GET_USER_KING_BATTLE_LINEUP_1565(1565, "获取特定玩家王者之战阵容"),
    CLI_GET_MASCOT_BLOOD_AND_STATE_1566(1566, "吉祥物的血量和吉祥物的状态"),
    NO_SUCH_CMD_0(0, "该条命令定义不存在");
    private int id;
    private String description;
    private static HashMap<Integer, Command> map;

    Command(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.id + "-" + this.description + "]";
    }

    public static HashMap<Integer, Command> getMap() {
        if (map != null) return map;
        map = new HashMap<>();
        Command[] values = Command.values();
        for (Command value : values) {
            map.put(value.getId(), value);
        }
        return map;
    }


    public static String findDescriptionById(int id) {
        Command command = getMap().get(id);
        return (command != null) ? command.toString() : Command.NO_SUCH_CMD_0.toString();
    }

    public static Command findById(int id) {
        return getMap().getOrDefault(id, Command.NO_SUCH_CMD_0);
    }
}