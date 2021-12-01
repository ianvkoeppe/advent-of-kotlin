use crate::io::IO;

pub fn part_one() -> String {
  return IO::read_lines(String::from("day1.txt")).join(",");
}