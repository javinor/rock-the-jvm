package com.rtjvm.scala.oop.files

import com.rtjvm.scala.oop.filesystem.FileSystemException

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {

  def hasEntry(name: String): Boolean =
    findEntry(name) != null

  def getAllFoldersInPath: List[String] = {
    path.substring(1).split(Directory.SEPARATOR).toList.filter(!_.isEmpty)
  }

  def findDescendant(path: List[String]): Directory =
    if (path.isEmpty) this
    else findEntry(path.head).asDirectory.findDescendant(path.tail)

  def addEntry(newEntry: DirEntry): Directory =
    new Directory(parentPath, name, contents :+ newEntry) // performance!?

  def findEntry(entryName: String): DirEntry =
    contents.find(_.name == entryName).getOrElse(null)

  def replaceEntry(entryName: String, newEntry: DirEntry): Directory =
    new Directory(parentPath, name, contents.filter(e => !e.name.equals(entryName)) :+ newEntry)

  def asDirectory: Directory = this

  override def getType: String = "Directory"

  override def asFile: File = throw new FileSystemException("A directory cannot be converted to a file")

  def isRoot: Boolean = parentPath.isEmpty

  override def isDirectory: Boolean = true

  override def isFile: Boolean = false
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")

  def empty(parentPath: String, name: String): Directory =
    new Directory(parentPath, name, List())
}