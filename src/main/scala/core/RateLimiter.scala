package core

import scala.collection.mutable

class RateLimiter(var n:Int) {
  private val map= mutable.Map[String,Int]().withDefaultValue(0)
  private def getWindowNumber:Long = {
      val time = System.nanoTime()/1000000000;
      return time/60;
  }
  def request(user:String):String = {
    val w = getWindowNumber
    val key = s"$user.$w"
    val value = map(key)
    map(key) = value +1
    if (value < n){
      "ok"
    } else {
      "limit exceeded"
    }
  }
}
