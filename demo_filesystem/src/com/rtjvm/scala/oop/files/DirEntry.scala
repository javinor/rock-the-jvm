package com.rtjvm.scala.oop.files

abstract class DirEntry(val parentPath: String, val name: String) {
  def path: String =
    if (parentPath == Directory.ROOT_PATH) parentPath + name
    else parentPath + Directory.SEPARATOR + name

  def asDirectory: Directory

  def asFile: File

  def isDirectory: Boolean

  def isFile: Boolean

  def getType: String
}
