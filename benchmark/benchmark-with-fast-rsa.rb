#!/usr/bin/env ruby

$LOAD_PATH << File.expand_path('../../lib', __FILE__)
if defined? JRUBY_VERSION
  require 'fast-rsa-engine'
end
require_relative 'benchmark'
