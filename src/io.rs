use std::fs;

pub struct IO;
impl IO {
  pub fn read_lines(file: String) -> Vec<String> {
    return Self::read(file).split("\n").map(String::from).collect();
  }

  pub fn read(file: String) -> String {
    let path = format!("src/resources/{}", file);
    return fs::read_to_string(path).unwrap();
  }
}