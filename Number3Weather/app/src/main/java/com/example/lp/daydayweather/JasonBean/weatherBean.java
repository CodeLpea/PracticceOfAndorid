package com.example.lp.daydayweather.JasonBean;

import java.util.List;

public class weatherBean {

    private List<HeWeatherBean> HeWeather;

    public List<HeWeatherBean> getHeWeather() {
        return HeWeather;
    }

    public void setHeWeather(List<HeWeatherBean> HeWeather) {
        this.HeWeather = HeWeather;
    }

    public static class HeWeatherBean {
        /**
         * basic : {"cid":"CN101190401","location":"苏州","parent_city":"苏州","admin_area":"江苏","cnty":"中国","lat":"31.29937935","lon":"120.61958313","tz":"+8.00","city":"苏州","id":"CN101190401","update":{"loc":"2019-02-12 09:56","utc":"2019-02-12 01:56"}}
         * update : {"loc":"2019-02-12 09:56","utc":"2019-02-12 01:56"}
         * status : ok
         * now : {"cloud":"100","cond_code":"104","cond_txt":"阴","fl":"2","hum":"83","pcpn":"0.0","pres":"1030","tmp":"4","vis":"8","wind_deg":"81","wind_dir":"东风","wind_sc":"2","wind_spd":"6","cond":{"code":"104","txt":"阴"}}
         * daily_forecast : [{"date":"2019-02-12","cond":{"txt_d":"中雨"},"tmp":{"max":"8","min":"5"}},{"date":"2019-02-13","cond":{"txt_d":"小雨"},"tmp":{"max":"8","min":"4"}},{"date":"2019-02-14","cond":{"txt_d":"小雨"},"tmp":{"max":"7","min":"6"}}]
         * aqi : {"city":{"aqi":"57","pm25":"40","qlty":"良"}}
         * suggestion : {"comf":{"type":"comf","brf":"较舒适","txt":"白天有雨，人们会感到有些凉意，但大部分人完全可以接受。"},"sport":{"type":"sport","brf":"较不宜","txt":"有较强降水，建议您选择在室内进行健身休闲运动。"},"cw":{"type":"cw","brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}}
         */

        private BasicBean basic;
        private UpdateBeanX update;
        private String status;
        private NowBean now;
        private AqiBean aqi;
        private SuggestionBean suggestion;
        private List<DailyForecastBean> daily_forecast;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public UpdateBeanX getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBeanX update) {
            this.update = update;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public AqiBean getAqi() {
            return aqi;
        }

        public void setAqi(AqiBean aqi) {
            this.aqi = aqi;
        }

        public SuggestionBean getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionBean suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public static class BasicBean {
            /**
             * cid : CN101190401
             * location : 苏州
             * parent_city : 苏州
             * admin_area : 江苏
             * cnty : 中国
             * lat : 31.29937935
             * lon : 120.61958313
             * tz : +8.00
             * city : 苏州
             * id : CN101190401
             * update : {"loc":"2019-02-12 09:56","utc":"2019-02-12 01:56"}
             */

            private String cid;
            private String location;
            private String parent_city;
            private String admin_area;
            private String cnty;
            private String lat;
            private String lon;
            private String tz;
            private String city;
            private String id;
            private UpdateBean update;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getParent_city() {
                return parent_city;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
            }

            public String getAdmin_area() {
                return admin_area;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                /**
                 * loc : 2019-02-12 09:56
                 * utc : 2019-02-12 01:56
                 */

                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class UpdateBeanX {
            /**
             * loc : 2019-02-12 09:56
             * utc : 2019-02-12 01:56
             */

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }

        public static class NowBean {
            /**
             * cloud : 100
             * cond_code : 104
             * cond_txt : 阴
             * fl : 2
             * hum : 83
             * pcpn : 0.0
             * pres : 1030
             * tmp : 4
             * vis : 8
             * wind_deg : 81
             * wind_dir : 东风
             * wind_sc : 2
             * wind_spd : 6
             * cond : {"code":"104","txt":"阴"}
             */

            private String cloud;
            private String cond_code;
            private String cond_txt;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            private String wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;
            private CondBean cond;

            public String getCloud() {
                return cloud;
            }

            public void setCloud(String cloud) {
                this.cloud = cloud;
            }

            public String getCond_code() {
                return cond_code;
            }

            public void setCond_code(String cond_code) {
                this.cond_code = cond_code;
            }

            public String getCond_txt() {
                return cond_txt;
            }

            public void setCond_txt(String cond_txt) {
                this.cond_txt = cond_txt;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public static class CondBean {
                /**
                 * code : 104
                 * txt : 阴
                 */

                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class AqiBean {
            /**
             * city : {"aqi":"57","pm25":"40","qlty":"良"}
             */

            private CityBean city;

            public CityBean getCity() {
                return city;
            }

            public void setCity(CityBean city) {
                this.city = city;
            }

            public static class CityBean {
                /**
                 * aqi : 57
                 * pm25 : 40
                 * qlty : 良
                 */

                private String aqi;
                private String pm25;
                private String qlty;

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }
            }
        }

        public static class SuggestionBean {
            /**
             * comf : {"type":"comf","brf":"较舒适","txt":"白天有雨，人们会感到有些凉意，但大部分人完全可以接受。"}
             * sport : {"type":"sport","brf":"较不宜","txt":"有较强降水，建议您选择在室内进行健身休闲运动。"}
             * cw : {"type":"cw","brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
             */

            private ComfBean comf;
            private SportBean sport;
            private CwBean cw;

            public ComfBean getComf() {
                return comf;
            }

            public void setComf(ComfBean comf) {
                this.comf = comf;
            }

            public SportBean getSport() {
                return sport;
            }

            public void setSport(SportBean sport) {
                this.sport = sport;
            }

            public CwBean getCw() {
                return cw;
            }

            public void setCw(CwBean cw) {
                this.cw = cw;
            }

            public static class ComfBean {
                /**
                 * type : comf
                 * brf : 较舒适
                 * txt : 白天有雨，人们会感到有些凉意，但大部分人完全可以接受。
                 */

                private String type;
                private String brf;
                private String txt;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class SportBean {
                /**
                 * type : sport
                 * brf : 较不宜
                 * txt : 有较强降水，建议您选择在室内进行健身休闲运动。
                 */

                private String type;
                private String brf;
                private String txt;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class CwBean {
                /**
                 * type : cw
                 * brf : 不宜
                 * txt : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
                 */

                private String type;
                private String brf;
                private String txt;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class DailyForecastBean {
            /**
             * date : 2019-02-12
             * cond : {"txt_d":"中雨"}
             * tmp : {"max":"8","min":"5"}
             */

            private String date;
            private CondBeanX cond;
            private TmpBean tmp;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public CondBeanX getCond() {
                return cond;
            }

            public void setCond(CondBeanX cond) {
                this.cond = cond;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public static class CondBeanX {
                /**
                 * txt_d : 中雨
                 */

                private String txt_d;

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }
            }

            public static class TmpBean {
                /**
                 * max : 8
                 * min : 5
                 */

                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }
        }
    }
}
